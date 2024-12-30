package scheduledevplus.lv2.user.dto.response;

import lombok.Getter;
import scheduledevplus.lv2.user.entity.User;

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
