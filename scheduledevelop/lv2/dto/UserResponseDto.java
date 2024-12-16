package scheduledevelop.lv2.dto;

import lombok.Getter;
import scheduledevelop.lv2.User;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String name;
    private final String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
