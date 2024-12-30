package scheduledevplus.lv2.user.dto.response;

import lombok.Getter;
import scheduledevplus.lv2.user.entity.User;

@Getter
public class SignUpResponseDto {

    private Long userId;
    private String email;
    private String username;

    private SignUpResponseDto(Long userId, String email, String username) {
        this.userId = userId;
        this.email = email;
        this.username = username;
    }

    /**
     * SIGNUP RESPONSE DTO 변환 로직 invoked by SIGNUP 메서드
     * @param user
     * @return 생성된 SignUpResponseDto 객체
     */
    public static SignUpResponseDto of(User user) {
        return new SignUpResponseDto(user.getUserId(), user.getEmail(), user.getUsername());
    }

}
