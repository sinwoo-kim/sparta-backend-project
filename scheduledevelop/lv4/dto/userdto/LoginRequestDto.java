package scheduledevelop.lv4.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;

    protected LoginRequestDto() {
    }
}
