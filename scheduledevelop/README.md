# Sparta 백엔드 5주차 과제 - 일정 관리 앱(Develop)

## 목차
1. [배경](#1-배경)
2. [사용 기술](#2-사용-기술)
3. [API 명세](#3-api-명세)
4. [ERD](#4-erd)
5. [트러블 슈팅](#5-트러블-슈팅)
6. 
***

### 1. 배경

이 과제는 지난 4주차 과제(JDBC로 만든 일정 관리 앱)을 JPA를 사용하여 발달시킨 버전으로,    
아래 내용을 이해하고 애플리케이션에 적용하기 위한 목표를 가지고 있습니다.

- Spring MVC, Validation 이해
- Servlet Filter와 Cookie/Session을 활용한 인증/인가 구현
- 연관관계 설정, CRUD

프로젝트 기간: 2024.12.11 ~ 2024.12.19

***

### 2. 사용 기술

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-0078D7?style=for-the-badge&logo=database&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

***

### 3. API 명세



<details>
<summary>Todo Controller</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

![image](https://github.com/user-attachments/assets/01dcf607-9b6d-4c23-8f80-edd244529b1f)

</details>
<details>
<summary>User Controller</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

![image](https://github.com/user-attachments/assets/a089e0ea-cee3-4eae-a8df-3b9a31fd57e4)
</details>

***

### 4. ERD

![image](https://github.com/user-attachments/assets/cf58b53f-ee9d-44ff-8e53-510cca7d04e3)

***

### 5. 트러블 슈팅

### Lv 1.  
<details>
<summary>1) HTTP객체 변환 과정에서 역직렬화 수행 안되는 문제</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

필드가 `final`로 선언되어 있을 때, 역직렬화 과정에서 문제가 발생할 수 있다.

**[해결]**

불변성을 유지해야되는 객체가 아니라면 `final`을 구지 사용할 필요 없다.

</details>

<details>
<summary>2) LocalDateTime 타입이 데이터베이스에 저장되지 않는 문제</summary>
<br> 
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

application @EnableJpaAuditing // Auditing 기능 비활성화 되있었음.

**[해결]**

Auditing 활성화

![image](https://github.com/user-attachments/assets/c07700c7-90c3-4be0-8081-6ee89f6a55a1)


</details>

<details>
<summary>3) 반환 된 Optional 객체를 dto로 변환하여 반환하는 과정에서 문제</summary>
<br>
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

`findById` 메서드가 반환하는 값이 `Optional<Todo>`이고, 반환 타입은 Todo Entity 타입을 필요로 하므로, 
변환되지 않은 `Optional<Todo>`를 바로 넘기면 컴파일 에러가 발생한다.

**[해결]**

.orElseThrow() 메서드를 사용하면 **Optional<T>**에서 값을 꺼내어 원하는 타입(T)으로 변환 가능하다. 
즉, Optional<Todo>를 Todo로 변환해줌.

![image](https://github.com/user-attachments/assets/671fde4f-67d2-48ef-9dc0-a5c4765e0cf9)

</details>


<details>
<summary>4) Dirty Checking 안되는 문제</summary>
<br>
   
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인&해결]**

클래스나 메서드에 `@Transactional`을 활성화시켜야 합니다.
</details>

  

### Lv 2 ~ 3.
 
<details>
<summary>1) 유저 테이블 생성 실패</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

유저 엔티티 @어노테이션 누락(`@Table`, `@Column`)
</details>

<details>
<summary>2) 유저 생성 시 "this.userRepository" is null 예외 발생</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

계층 생성 시 @AllArgsConstructor 누락
</details>

<details>
<summary>3) todo 삭제 후 재 생성 시 id가 밀리는 현상</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[원인]**

id가 밀리는게 아니라 요청한 id는 userId 였고, 응답 Id는 todoId 였음.
dto에 필드 네이밍을 명확하게 만들어야 햇갈리지 않는다.

</details>


### Lv 4 ~ 6. 

<details>
<summary>1) redirect:/session-home을 건너뛰고 필터가 실행되는 문제</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[왜 리다이렉트 후 필터가 실행되었나?]**

리다이렉트는 새로운 요청이므로, 기존 요청과는 독립적인 HTTP 요청-응답 사이클로 처리됩니다. 이 때문에 리다이렉트 후의 요청 역시 필터를 다시 통과해야 한다.
</details>


<details>
<summary>2) 로그인 기능 실행 과정에서 `ClassCastException` 예외 발생</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[에러코드]**

LoginResponseDto cannot be cast to class scheduledevelop.lv4.dto.userdto.UserResponseDto

**[배경]**

Session 저장 시 코드 간소화를 위해서 변환 된 `loginResponseDto` 객체를 바로 전달하였습니다.

![과제 레벨6 트러블슈팅](https://github.com/user-attachments/assets/300146e5-de1b-4f22-8a1f-c04f1638b3cb)


**[원인]**

LoginResponseDto 객체를 UserResponseDto 타입으로 캐스팅하려고 했기 때문

![과제 레벨6 트러블슈팅2](https://github.com/user-attachments/assets/32680b97-b7dd-4c2a-b785-b97b616184b1)

**[해결]**

세션 저장 타입과 동일한 타입(LoginResponseDto)으로 캐스팅 

![image](https://github.com/user-attachments/assets/3ef7b306-41bf-4fc8-bbb2-3165094d0344)


**[의문]**

loginResponseDto를 session 바로 저장해도 괜찮은가?

</details>


<details>
<summary>3) 컨트롤러 -> 템플릿 처리 중 런타임 예외 발생</summary>
<br>   
<!-- summary 아래 한칸 공백 두어야함 -->

**[에러코드]**

Exception processing template "session-home": Error resolving template [session-home], template might not exist or might not be accessible by any of the configured Template Resolvers

**[배경]**

뷰 -> 모델을 통한 응답 처리를 구현하는 중 templates을 디렉토리로 관리하고자 (templates/user/...) 경로로 변경하였습니다.

**[왜 경로 문제가 발생했는가?]** 

`@RequestMapping`에 적용된 경로를 자동 적용될 것으로 착각하여 메서드 반환 경로에 포함시키지 않은 것이 원인이었습니다.

![image](https://github.com/user-attachments/assets/f222aebd-11be-480a-a574-653efd4426dc)

**[해결]**

모든 메서드 반환 경로에 /user/를 포함시켰습니다.

![image](https://github.com/user-attachments/assets/4201ae51-65d7-47b8-99ab-ac906a4fb165)
</details>

