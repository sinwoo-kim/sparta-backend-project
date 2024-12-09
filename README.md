# 일정관리앱 만들기

 ## 트러블 슈팅
 ### Lv 1
 - [@PathVariable에 변수 이름을 명시하지 않으면 IllegalArgumentException 예외 발생](#Lv-1-트러블-슈팅)
 ### Lv 2
 - [클라이언트 PUT 요청 시 MissingServletRequestParameterException 예외 발생](#Lv-2-트러블-슈팅)

***
## Lv 1 트러블 슈팅
### `@PathVariable`에 변수 이름을 명시하지 않으면 `IllegalArgumentException` 예외 발생

#### 1. 배경

`@PathVariable` 변수 이름과 매개변수 명이 동일하면 변수 이름을 생략해도 Spring에서 자동으로 매핑을 해줘야 하지만 자동 매핑이 되지 않는 예외가 발생하였습니다.

![image](https://github.com/user-attachments/assets/ee7653a3-486b-46c5-a90f-fbe71830ce36)

#### 2. 원인

이 예외는 Spring Boot 3.2로 넘어오면서 몇몇 어노테이션의 인식 문제가 있는데 `@PathVariable`도 그 중 하나라는 걸 알게 됐습니다.

#### 3. 해결

인텔리제이 Settings -> Build and Run 을 Gradle로 설정하면 포스트맨에서 정상 처리 되는 것을 확인할 수 있습니다.

![image](https://github.com/user-attachments/assets/3090f638-fb7c-4a0f-8cf4-ae03b1b91275)

#### 4. 결론

협업 시 다른 개발자가 Gradle로 설정하지 않는다면 매핑 문제가 발생할 수 있으므로 Spring을 믿지 말고 명시하는 습관을 들이기로 했습니다.

#### 5. 참고 자료
https://dajeongdev.github.io/wiki/2024/01/17/pathvariable-name-missing.html

***
## Lv 2 트러블 슈팅
### 단건 일정 수정하기 기능 요청 시 MissingServletRequestParameterException 예외 발생

#### 1. 배경

기능 구현을 마친 후 빌드가 완료되었으나 포스트맨에서 PUT 요청 시 아래와 같은 예외가 발생하였습니다.

![image](https://github.com/user-attachments/assets/074fa524-3fd7-4566-8712-6782fb786253)

#### 2. 원인

실수로 어노테이션을 `@RequsetParam`으로 입력한 것이 원인이었습니다.

#### 3. 해결

@PathVariable로 수정 후 포스트맨에서 정상 처리 되는 것을 확인

![image](https://github.com/user-attachments/assets/c7d75bc2-13c2-4334-819f-226df7130e2b)

#### 4. 결론

@PathVariable의 문법에 @RequestParam을 사용 시 컴파일 에러가 나지 않는 것을 알게 되었고,
이와 같은 매핑된 핸들러 메서드를 못찾는 예외가 발생했을 때 어노테이션을 확인해야 된다는 것을 배울 수 있었습니다.


