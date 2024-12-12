# Project Name

lv4. 로그인 인증/인가

- [설명] Cookie/Session을 활용
- [설명] 필터를 활용한 인증 처리(@Configuration로 필터 등록)
- [조건] 이메일과 비밀번호 활용해 로그인 기능 구현
- [조건] 예외처리, 불일치 시 401 반환하기.

1. request 객체를 `HttpServletRequest`로 다운 캐스팅하는 이유?   
`HttpServletRequest`는 `ServletRequest` 인터페이스를 확장한 것으로 특별한 메서드 추가 제공한다.

2. `httpRequest` 변수에 저장해서 `getRequestURI()`호출하는 이유?   
매번 다운 캐스팅하는 것은 불필요한 작업이다.

3. 빈


