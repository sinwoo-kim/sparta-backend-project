//package scheduledevelop.lv1.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.PatternMatchUtils;
//
//import java.io.IOException;
//
//
//@Slf4j
//public class LoginFilter implements Filter {
//    // 인증을 하지 않아도될 URL Path 배열
//    private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout"};
//
//    // filter에는 URL이 없는데 어떻게 먼저 실행되는가?
//
//    @Override
//    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {
//
//        // 공통 구현
//        HttpServletRequest httpServletRequest = (HttpServletRequest) Request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) Response;
//        String requestURI = httpServletRequest.getRequestURI();
//
//        log.info("로그인 필터 로직 실행");
//
//        // URI 검증 :: 포함하지 않는다면 true
//        if (!isWhiteList(requestURI)) {
//
//            HttpSession session = httpServletRequest.getSession(false);
//
//            if (session == null || session.getAttribute("sessionKey") == null) {
//                throw new RuntimeException("로그인 해주세요.");
//            }
//            // 성공
//            log.info("로그인에 성공했습니다.");
//        }
//        Chain.doFilter(httpServletRequest, httpServletResponse);
//
//    }
//    // URI 검증 메서드 :: 하나라도 일치하면 true를 반환한다.
//    private boolean isWhiteList(String RequestURI) {
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, RequestURI);
//    }
//}