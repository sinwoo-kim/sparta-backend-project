package scheduledevelop.lv4.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor // 생성자 자동 생성
@NoArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String password;
    private String username;

}
