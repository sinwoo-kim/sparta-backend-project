package scheduledevelop.lv2.dto.tododto;

import lombok.Getter;
import scheduledevelop.lv2.Todo;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String username;
    private final String title;
    private final String contents;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
    }

}
