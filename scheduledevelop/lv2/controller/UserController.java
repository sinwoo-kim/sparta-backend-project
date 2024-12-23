package scheduledevelop.lv2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduledevelop.lv2.dto.userdto.UserRequestDto;
import scheduledevelop.lv2.dto.userdto.UserResponseDto;
import scheduledevelop.lv2.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor // 생성자 주입 로직 자동 생성
public class UserController {

    private final UserService userService;

    // 1. CREATE USER
    @PostMapping
    public ResponseEntity<UserResponseDto> createUserAPI(@RequestBody UserRequestDto requestDto) {
        log.info("createUserAPI를 실행합니다.");
        UserResponseDto createdUser = userService.createUser(requestDto.getName(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 2. READ USERS ALL
    @GetMapping
    public List<UserResponseDto> findUsersAPI() {
        log.info("findUsersAPI를 실행합니다.");
        return userService.findUsers();
    }

    // 3. READ USER
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserAPI(@PathVariable("id") Long id) {
        log.info("findUserAPI를 실행합니다.");
        UserResponseDto foundUser = userService.findUser(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    // 4. MODIFY USER (name, email)
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> modifyUserAPI(@PathVariable("id") Long id,
                                                         @RequestBody UserRequestDto requestDto) {
        log.info("modifyUserAPI를 실행합니다.");
        UserResponseDto modifyUser = userService.modifyUser(id, requestDto.getName(), requestDto.getEmail());
        return new ResponseEntity<>(modifyUser, HttpStatus.OK);
    }

    // 5. DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserAPI(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deletion successful");
    }
}
