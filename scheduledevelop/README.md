# Project Name

lv1.  

[변경] 작성일, 수정일 필드는 JPA Auditing을 활용

1. Entity 어노테이션 설정 
2. JPA에서의 컨트롤러 변경점?
3. DB로 어떻게 전송하는가?
- Repository JpaRepository<Todo, Long>를 extends 함.

트러블슈팅

lv1. 
- TodoRequestDto : HTTP객체 변환 과정에서 역직렬화 수행 안되는 문제
에러코드
- 시간 DB에 저장되지 않는 문제 : application @EnableJpaAuditing // Auditing 기능 비활성화 되있었음.
- 선택 조회 기능 구현 시 서비스 가져온 객체를 dto로 변환하여 반환하는 과정에서 문제
원인: findById 메서드가 반환하는 값이 Optional<Todo>이고, dto는 Todo 타입을 필요로 하므로, 변환되지 않은 Optional<Todo>를 바로 넘기면 컴파일 에러가 발생한디.
해결: .orElseThrow() 메서드를 사용하면 **Optional<T>**에서 값을 꺼내어 원하는 타입(T)으로 변환해주는 메서드입니다. 즉, Optional<Todo>를 Todo로 변환해줍니다.



lv4. 로그인 인증/인가

- [설명] Cookie/Session을 활용
- [설명] 필터를 활용한 인증 처리(@Configuration로 필터 등록)
- [조건] 이메일과 비밀번호 활용해 로그인 기능 구현
- [조건] 예외처리, 불일치 시 401 반환하기.

1. request 객체를 `HttpServletRequest`로 다운 캐스팅하는 이유?   
`HttpServletRequest`는 `ServletRequest` 인터페이스를 확장한 것으로 특별한 메서드 추가 제공한다.

2. `httpRequest` 변수에 저장해서 `getRequestURI()`호출하는 이유?   
매번 다운 캐스팅하는 것은 불필요한 작업이다.

3. 인증 검증에 사용되는 효과적인 도구
PatternMatchUtils.simpleMatch(WhiteList, requestURI) // 하나라도 있으면 true 반환


