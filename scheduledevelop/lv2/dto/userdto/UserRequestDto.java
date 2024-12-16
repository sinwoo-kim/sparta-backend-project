package scheduledevelop.lv2.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto{

    private String name;
    private String email;

//    public UserRequestDto(String name, String email) {
//        this.name = name;
//        this.email = email;
//    }
}
