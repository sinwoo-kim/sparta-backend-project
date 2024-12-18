package scheduledevelop.lv4.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import scheduledevelop.lv4.Const;
import scheduledevelop.lv4.dto.userdto.LoginRequestDto;
import scheduledevelop.lv4.dto.userdto.LoginResponseDto;
import scheduledevelop.lv4.dto.userdto.UserResponseDto;
import scheduledevelop.lv4.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class SessionUserController {

    // 의존성 주입
    private final UserService userService;


    // 로그인 페이지 (GET)
    @GetMapping("/login")
    public String loginPage() {
        return "session-login"; // 로그인 페이지 템플릿 반환
    }

    @PostMapping("/session-login")
    public String login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {

        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        log.info("loginResponseDto = " + loginResponseDto);

//        Long userId = loginResponseDto.getId();
//        UserResponseDto loginUser = userService.findUser(userId);

        // 세션 조회
        HttpSession session = request.getSession();
        // Session 저장 (변환 된 loginResponseDto 객체를 전달하면 안되는가?)
        session.setAttribute(Const.LOGIN_USER, loginResponseDto);

        return "redirect:/user/session-home";

        //

    }


}
