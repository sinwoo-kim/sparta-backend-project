package scheduledevelop.lv4.config;//package scheduledevelop.lv1.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Slf4j
public class LoginFilter implements Filter {
    // 인증을 하지 않아도 될 URL Path 배열
    private static final String[] WHITE_LIST = {"/", "/todos", "/todos/*", "/users", "/users/*"};

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {
        log.info("로그인 필터 로직 실행");

        // 공통 구현
        HttpServletRequest httpServletRequest = (HttpServletRequest) Request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) Response;
        String requestURI = httpServletRequest.getRequestURI();


        // URI 검증 :: 화이트리스트에 속하지 않는 URI만 검증 수행
        if (!isWhiteList(requestURI)) {
            // 로그인 검증을 위해 세션을 가져옵니다.
            log.info("isWhiteList 조건 실행");
            HttpSession session = httpServletRequest.getSession(false);
            log.info("session = " + session);
            // 로그인했는지 안했는지 검증
            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                log.info("LoginFilter Request URI: {}", requestURI);
//                httpServletResponse.sendRedirect("/users/session-login");
                // 에러 코드 401
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인을 해주세요.");
            }
            // 성공
            log.info("로그인에 성공했습니다.");
        }
        // 다음 필터가 없으면 Servlet -> Controller 실행
        Chain.doFilter(httpServletRequest, httpServletResponse);

    }

    // URI 검증 메서드 :: 하나라도 일치하면 true를 반환한다.
    private boolean isWhiteList(String RequestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, RequestURI);
    }
}