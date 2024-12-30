package scheduledevelop.lv4.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import scheduledevelop.lv4.user.dto.response.LoginResponseDto;
import scheduledevelop.lv4.user.service.UserService;

@Controller
@RequiredArgsConstructor // 생성자 주입이나 불변 객체 설계 시 사용
@RequestMapping("/users")
@Slf4j
public class SessionHomeController {

    // 의존성 주입
    private final UserService userService;

    // GetMapping
    @GetMapping("/session-home")
    public String home(HttpServletRequest request, Model model) {
        log.info("Seesion-HomeController 실행");
        // 세션 검증
        HttpSession session = request.getSession(false);

        // 유저정보 검증
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);

        model.addAttribute("loginUser", loginUser);
        return "/users/session-home";
    }
}
