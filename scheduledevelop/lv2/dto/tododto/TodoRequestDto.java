package scheduledevelop.lv2.dto.tododto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDto {

    private Long id;
    private String username;
    private String title;
    private String contents;

//    private final String authorName;
//    private final String title;
//    private final String contents;


//    public TodoRequestDto(String authorName, String title, String contents) {
//        this.authorName = authorName;
//        this.title = title;
//        this.contents = contents;
//    }
}
