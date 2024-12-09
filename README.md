# 일정관리앱 만들기

## 트러블 슈팅

### Lv 1
- [@PathVariable에 변수 이름을 명시하지 않으면 IllegalArgumentException 예외 발생](#lv1-1)

***

### `@PathVariable`에 변수 이름을 명시하지 않으면 `IllegalArgumentException` 예외 발생

![image](https://github.com/user-attachments/assets/3bd3b34d-2d19-4f22-9d59-bde670edde75)

#### 발단 & 원인

`@PathVariable` 변수 이름과 매개변수 명이 동일하면 변수 이름을 생략해도 Spring에서 자동으로 매핑을 해줘야 하지만 자동 매핑이 되지 않는 예외가 발생하였습니다.
이 예외는 Spring Boot 3.2로 넘어오면서 몇몇 어노테이션의 인식 문제가 있는데 `@PathVariable`도 그 중 하나라고 합니다.

#### 해결책

![image](https://github.com/user-attachments/assets/3090f638-fb7c-4a0f-8cf4-ae03b1b91275)

Settings -> Build and Run 을 Gradle로 설정하면 정상 매핑이 되는 것을 확인할 수 있습니다.

#### 결론

협업 시 다른 개발자가 Gradle로 설정하지 않는다면 매핑 문제가 발생할 수 있으므로 Spring을 믿지 말고 명시하는 습관을 들이는 것이 좋을 것 같습니다.

참고 자료 : https://dajeongdev.github.io/wiki/2024/01/17/pathvariable-name-missing.html
