package scheduledevelop.lv4.dto.userdto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import scheduledevelop.lv4.entity.User;


@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String email;
    private String username;

    // 생성 잠금
    private LoginResponseDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    /**
     * LOGIN RESPONSE DTO 변환 로직 invoked by LOGIN 메서드
     * @param user
     * @return 생성된 LoginResponseDto 객체
     */
    public static LoginResponseDto of(User user) {
        return new LoginResponseDto(user.getId(), user.getEmail(), user.getUsername());
    }
}