package scheduledevelop.lv4.dto.tododto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoCreateRequestDto {

    private Long userId;
    private String username;
    private String title;
    private String contents;
}