# 일정관리앱 서비스
간단하게 일정을 추가, 조회, 수정, 삭제할 수 있는 기능을 가진 일정관리앱 입니다.

## 1. 배경
Sparta 백엔드 Spring 4주차 과제로 주어진 일정관리 앱 만들기는 다음과 같은 도전적인 조건 속에서 진행된 프로젝트입니다:

- 대상: 노베이스 비전공자
- 주어진 시간: 약 7일

이 프로젝트는 Spring Framework와 Database를 학습하면서, 필수 기능인 CRUD(Create, Read, Update, Delete)를 구현하는 것입니다.


## 2. 사용 기술
<!-- Java -->
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## 3. API 명세

|기능|Method|Request|Response| 상태 코드|
|------|---|---|---|---|
|Todo 생성|`CREATE`|/todo|등록 정보|CREATED|
|Todo 전체 조회|`GET`|/todo|다건 응답 정보|OK|
|Todo 단건 조회|`GET`|/{id}|단건 응답 정보|OK|
|Todo 단건 수정|`PATCH`|/{id}|수정 정보|OK|
|Todo 단건 삭제|`DELETE`|/{id}|-|OK|

## 4. ERD

![image](https://github.com/user-attachments/assets/d7975149-12d1-4033-93c5-9b88fb23696b)



 ## 5. 트러블 슈팅
 ### Lv 1
 - [@PathVariable에 변수 이름을 명시하지 않으면 IllegalArgumentException 예외 발생](#Lv-1-트러블-슈팅)
 ### Lv 2
 - [클라이언트 PUT 요청 시 MissingServletRequestParameterException 예외 발생](#Lv-2-트러블-슈팅)
 - [DELETE 기능 구현 중 비밀번호 검증 추가 시 예외 발생](#비밀번호-검증-로직-추가-시-DELETE가-안되는-문제)

***
## Lv 1 트러블 슈팅
### `@PathVariable`에 변수 이름을 명시하지 않으면 `IllegalArgumentException` 예외 발생

#### 1) 배경

`@PathVariable` 변수 이름과 매개변수 명이 동일하면 변수 이름을 생략해도 Spring에서 자동으로 매핑을 해줘야 하지만 자동 매핑이 되지 않는 예외가 발생하였습니다.

![image](https://github.com/user-attachments/assets/ee7653a3-486b-46c5-a90f-fbe71830ce36)

#### 2) 원인

이 예외는 Spring Boot 3.2로 넘어오면서 몇몇 어노테이션의 인식 문제가 있는데 `@PathVariable`도 그 중 하나라는 걸 알게 됐습니다.

#### 3) 해결

인텔리제이 Settings -> Build and Run 을 Gradle로 설정하면 포스트맨에서 정상 처리 되는 것을 확인할 수 있습니다.

![image](https://github.com/user-attachments/assets/3090f638-fb7c-4a0f-8cf4-ae03b1b91275)

#### 4) 결론

협업 시 다른 개발자가 Gradle로 설정하지 않는다면 매핑 문제가 발생할 수 있으므로 Spring을 믿지 말고 명시하는 습관을 들이기로 했습니다.

#### 5) 참고 자료
https://dajeongdev.github.io/wiki/2024/01/17/pathvariable-name-missing.html

***
## Lv 2 트러블 슈팅
### 단건 일정 수정하기 기능 요청 시 MissingServletRequestParameterException 예외 발생

#### 1) 배경

기능 구현을 마친 후 빌드가 완료되었으나 포스트맨에서 PUT 요청 시 아래와 같은 예외가 발생하였습니다.

![image](https://github.com/user-attachments/assets/074fa524-3fd7-4566-8712-6782fb786253)

#### 2) 원인

실수로 어노테이션을 `@RequsetParam`으로 입력한 것이 원인이었습니다.

#### 3) 해결

`@PathVariable`로 수정 후 포스트맨에서 정상 처리 되는 것을 확인

![image](https://github.com/user-attachments/assets/c7d75bc2-13c2-4334-819f-226df7130e2b)

#### 4) 결론

`@PathVariable`의 문법에 `@RequestParam`을 사용 시 컴파일 에러가 나지 않는 것을 알게 되었고,   
이와 같은 매핑된 핸들러 메서드를 못찾는 예외가 발생했을 때 어노테이션을 확인해야 된다는 것을 배울 수 있었습니다.

***

### 비밀번호 검증 로직 추가 시 DELETE가 안되는 문제

#### 1) 배경

클라이언트로부터 비밀번호를 요청받아 서버 데이터와 비교 검증하는 `DELETE` 기능 구현 中   
실행 시 `DELETE`가 정상 처리되지 않는 문제가 발생하였습니다.

![image](https://github.com/user-attachments/assets/1182a8b7-50d2-4961-b652-d29855d8c4c6)

#### 2) 전개

자정이 넘은 시간이었기에 튜터님의 도움을 받을 수 없었고,    
GPT를 활용해서 코드 오류를 찾고자 노력했지만 에러 추적 범위를 좁히는데 실패했습니다.

#### 절정 :: `log`를 사용하다.

지금까지 사용해 본 적 없던 디버깅이란 걸 시도하게 되었습니다. 
서버에서 조회한 `optionalTodo` 객체를 `log`를 활용해서 디버깅 해본 결과 비밀번호를 정상적으로 받지 못했음을 확인 했습니다.

![image](https://github.com/user-attachments/assets/52adc009-8703-4659-b6be-d94f631293b0)

#### 2) 원인

원인은 코드를 재사용할 때 그 로직을 그대로 사용한데 있었습니다. 서버로부터 비밀번호를 받아와야 했지만 비밀번호를 받아오는 로직이
없었기 때문에 0으로 가져온 것이었습니다.

![image](https://github.com/user-attachments/assets/eee31703-a357-4256-9054-d9f06bfe2c75)

#### 3) 해결

로직을 추가한 결과 `DELETE`가 정상 처리 됐습니다.

![image](https://github.com/user-attachments/assets/b74068da-c2ba-41c8-8c6d-5d81338ab36f)


#### 4) 결론

디버깅이라는 걸 어렵다고 생각했었는데, 정말 쉽다?는 경험을 하게 되었고, `log`를 활용하여 추적 범위를 빠르게 좁히는 방법에 대해
배웠습니다. 그리고 `Mapper` 메서드가 서버에서 실제로 데이터를 가져오는 역할을 수행하는 기능이었다는 것을 이해하게 되었습니다.

