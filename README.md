# 일정관리앱 만들기

## 트러블 슈팅

### Lv 1
- [@PathVariable에 변수 이름을 명시하지 않으면 IllegalArgumentException 예외 발생](#lv1-1)

***

#### `@PathVariable`에 변수 이름을 명시하지 않으면 `IllegalArgumentException` 예외 발생

`@PathVariable`에 변수 이름을 명시하지 않으면 스프링이 URL 경로 변수와 매개변수를 연결하지 못해 예외가 발생할 수 있습니다.
