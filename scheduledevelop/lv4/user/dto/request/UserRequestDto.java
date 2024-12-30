package scheduledevelop.lv4.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto{

    private String name;
    private String password;
    private String email;

//    public UserRequestDto(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
}
