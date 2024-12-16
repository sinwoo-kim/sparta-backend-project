package scheduledevelop.lv2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scheduledevelop.lv2.Todo;
import scheduledevelop.lv2.User;
import scheduledevelop.lv2.dto.TodosResponseDto;
import scheduledevelop.lv2.repository.UserRepository;
import scheduledevelop.lv2.dto.UserRequestDto;
import scheduledevelop.lv2.dto.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public UserResponseDto createUser(String name, String email) {
        User user = new User(name, email);
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    public List<UserResponseDto> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    public UserResponseDto findUser(Long id) {
        User foundUser = userRepository.findByIdorElseThrow(id);
        return new UserResponseDto(foundUser);
    }

    public UserResponseDto modifyUser(Long id, String name, String email) {
        User foundUser = userRepository.findByIdorElseThrow(id);
        foundUser.setName(name);
        foundUser.setEmail(email);

        return new UserResponseDto(foundUser);
    }

    public void deleteUser(Long id) {
        User foundUser = userRepository.findByIdorElseThrow(id);
        userRepository.deleteById(id);
    }
}
