package scheduledevelop.lv2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scheduledevelop.lv2.entity.User;
import scheduledevelop.lv2.repository.UserRepository;
import scheduledevelop.lv2.dto.userdto.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public UserResponseDto createUser(String name, String password, String email) {
        User user = new User(name, password, email);
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    public UserResponseDto findUser(Long id) {
        User foundUser = userRepository.findByUserIdorElseThrow(id);
        return new UserResponseDto(foundUser);
    }

    @Transactional
    public UserResponseDto modifyUser(Long id, String name, String email) {
        User foundUser = userRepository.findByUserIdorElseThrow(id);
        foundUser.setUsername(name);
        foundUser.setEmail(email);

        return new UserResponseDto(foundUser);
    }

    public void deleteUser(Long id) {
        User foundUser = userRepository.findByUserIdorElseThrow(id);
        userRepository.deleteById(id);
    }
}
