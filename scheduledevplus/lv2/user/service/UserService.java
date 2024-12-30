package scheduledevplus.lv2.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import scheduledevplus.lv2.config.PasswordEncoder;
import scheduledevplus.lv2.user.dto.request.LoginRequestDto;
import scheduledevplus.lv2.user.dto.request.SignUpRequestDto;
import scheduledevplus.lv2.user.dto.response.LoginResponseDto;
import scheduledevplus.lv2.user.dto.response.SignUpResponseDto;
import scheduledevplus.lv2.user.dto.response.UserResponseDto;
import scheduledevplus.lv2.user.entity.User;
import scheduledevplus.lv2.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    /**
     * SIGN UP 메서드
     * @param signUpRequestDto
     * @return Dto 변환 로직을 사용
     */
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        // 비밀번호 암호화
        String rawPassword = signUpRequestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        signUpRequestDto.setPassword(encodedPassword);

        // Dto 변환 로직 사용
        User newUser = User.createFromSignUpDto(signUpRequestDto);
        User savedUser = userRepository.save(newUser);
        return SignUpResponseDto.of(savedUser);
    }

    /**
     * LOGIN 메서드
     * @param loginRequestDto
     * @return DTO 변환 로직을 사용
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // dto -> User 변환 로직 구현
        User loginRequest = User.toEntityFromLoginRequestDto(loginRequestDto);
        // 사용자 조회
        User findLoginUser = userRepository.findByEmailOrElseThrow(loginRequest.getEmail());
        log.info("findLoginUser: {}" + findLoginUser);

        if(!passwordEncoder.matches(loginRequest.getPassword(),findLoginUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        return LoginResponseDto.of(findLoginUser);
    }

    // READ :: ALL
    public List<UserResponseDto> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    // READ :: FIND USER BY ID
    public UserResponseDto findUser(Long id) {
        User findByIdFromFindUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findByIdFromFindUser);
    }

    // MODIFY
    @Transactional
    public UserResponseDto modifyUser(Long id, String name, String email) {
        User findByIdFromModifyUser = userRepository.findByIdOrElseThrow(id);
        findByIdFromModifyUser.setUsername(name);
        findByIdFromModifyUser.setEmail(email);

        return new UserResponseDto(findByIdFromModifyUser);
    }

    // DELETE
    public void deleteUser(Long id) {
        User findByIdFromDeleteUser = userRepository.findByIdOrElseThrow(id);
        userRepository.deleteById(id);
    }
}
