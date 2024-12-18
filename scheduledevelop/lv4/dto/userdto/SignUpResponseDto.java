package scheduledevelop.lv4.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import scheduledevelop.lv4.entity.User;

@Getter
public class SignUpResponseDto {

    private Long id;
    private String email;
    private String username;

    private SignUpResponseDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    /**
     * SIGNUP RESPONSE DTO 변환 로직 invoked by SIGNUP 메서드
     * @param user
     * @return 생성된 SignUpResponseDto 객체
     */
    public static SignUpResponseDto of(User user) {
        return new SignUpResponseDto(user.getId(), user.getEmail(), user.getUsername());
    }

}
