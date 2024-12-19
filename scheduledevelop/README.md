# Project Name

lv1.  

[변경] 작성일, 수정일 필드는 JPA Auditing을 활용

1. Entity 어노테이션 설정 
2. JPA에서의 컨트롤러 변경점?
3. DB로 어떻게 전송하는가?
- Repository JpaRepository<Todo, Long>를 extends 함.

### 트러블슈팅

- TodoRequestDto : HTTP객체 변환 과정에서 역직렬화 수행 안되는 문제

- 시간 DB에 저장되지 않는 문제   
원인:application @EnableJpaAuditing // Auditing 기능 비활성화 되있었음.
 
- 선택 조회 기능 구현 시 서비스 가져온 객체를 dto로 변환하여 반환하는 과정에서 문제   
원인: findById 메서드가 반환하는 값이 Optional<Todo>이고, dto는 Todo 타입을 필요로 하므로, 변환되지 않은 Optional<Todo>를 바로 넘기면 컴파일 에러가 발생한디.
해결: .orElseThrow() 메서드를 사용하면 **Optional<T>**에서 값을 꺼내어 원하는 타입(T)으로 변환해주는 메서드입니다. 즉, Optional<Todo>를 Todo로 변환해줍니다.

- Dirty Checking 안되는 문제   
원인: 클래스나 메서드에 @Transactional을 활성화시켜야 합니다.

lv2.

[추가] 유저 테이블 생성(유저명, 이메일, 작성일) // 작성일 필드는 JPA Auditing을 활용   
[변경] 연관 관계 구현(일정은 작성 유저명 필드 -> 유저 고유 식별자 필드)

### 트러블 슈팅

- 유저 테이블 생성 실패   
원인: 유저 엔티티 @어노테이션 누락(@Table, @Column)


- 유저 생성 시 "this.userRepository" is null 예외 발생   
원인: 계층 생성 시 @AllArgsConstructor 누락


- 연관 관계 매핑 (id)

- todo 삭제 후 재 생성 시 id가 밀리는 현상
[원인] id가 밀리는게 아니라 요청한 id는 userId 였고, 응답 Id는 todoId 였음.
[결론] dto에 필드 네이밍을 명확하게 만들어 줘야 햇갈리지 않음.


lv4. 로그인 인증/인가

- [설명] Cookie/Session을 활용
- [설명] 필터를 활용한 인증 처리(@Configuration로 필터 등록)
- [조건] 이메일과 비밀번호 활용해 로그인 기능 구현
- [조건] 예외처리, 불일치 시 401 반환하기.
- [개인] 입력값 검증: @Valid와 검증 어노테이션(@Email, @NotBlank) 사용 (Controller 레벨).

1. request 객체를 `HttpServletRequest`로 다운 캐스팅하는 이유?   
`HttpServletRequest`는 `ServletRequest` 인터페이스를 확장한 것으로 특별한 메서드 추가 제공한다.

2. `httpRequest` 변수에 저장해서 `getRequestURI()`호출하는 이유?   
매번 다운 캐스팅하는 것은 불필요한 작업이다.

3. 인증 검증에 사용되는 효과적인 도구
PatternMatchUtils.simpleMatch(WhiteList, requestURI) // 하나라도 있으면 true 반환

### 트러블 슈팅

필터 적용 시 매핑된 /{id}에 접근할 수 없음

1. redirect:/session-home을 건너뛰고 필터가 실행되는 문제

- 왜 리다이렉트 후 필터가 실행되었나?   
리다이렉트는 새로운 요청이므로, 기존 요청과는 독립적인 HTTP 요청-응답 사이클로 처리됩니다. 이 때문에 리다이렉트 후의 요청 역시 필터를 다시 통과해야 합니다.

2. 로그인 기능 실행 과정에서 ClassCastException 예외 발생
[에러코드] LoginResponseDto cannot be cast to class scheduledevelop.lv4.dto.userdto.UserResponseDto
[배경] Session 저장 시 코드 간소화를 위해서 변환 된 loginResponseDto 객체를 바로 전달하였습니다.
[원인] LoginResponseDto 객체를 UserResponseDto 타입으로 캐스팅하려고 했기 때문
[해결] 세션 저장 타입과 동일한 타입(LoginResponseDto)으로 캐스팅 
[의문] loginResponseDto를 session 바로 저장해도 괜찮은가?
[결론] 

3. 컨트롤러 -> 템플릿 처리 중 런타임 예외 발생
[에러코드] Exception processing template "session-home": Error resolving template [session-home], template might not exist or might not be accessible by any of the configured Template Resolvers
[배경] 뷰 -> 모델을 통한 응답 처리를 구현하는 중 templates을 디렉토리로 관리하고자 (templates/user/...) user 디렉토리를 생성하였습니다.
[원인] 왜 경로 문제가 발생했는가? @RequestMapping에 적용된 경로를 자동 적용될 것으로 착각하여 메서드 반환 경로에 포함시키지 않은 것이 원인이었습니다.
![img_1.png](img_1.png)
[해결] html 파일 경로를 templates/todo/... -> templates/... 로 변경하였습니다.  

lv5 다양한 예외 처리 적용

[추가] Validation활용
[조건] @Pattern 사용해서 회원가입, EMail 데이터 검증

lv6 비밀번호 암호화

1. 의존성 추가
   implementation 'org.springframework.boot:spring-boot-starter-security'
   implementation 'org.springframework.boot:spring-boot-starter-web' // (선택) 웹 기능을 사용 중이라면 필요
   implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // (선택) JPA를 사용할 경우

lv7


