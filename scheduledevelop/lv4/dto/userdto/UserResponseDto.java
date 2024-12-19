package scheduledevelop.lv4.dto.userdto;

import lombok.Getter;
import scheduledevelop.lv4.entity.User;

@Getter
public class UserResponseDto {

    private final Long userId;
    private final String name;
    private final String email;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getUsername();
        this.email = user.getEmail();
    }
}
