package scheduledevelop.lv2.dto;

import lombok.Getter;
import scheduledevelop.lv2.Todo;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String authorName;
    private final String title;
    private final String contents;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.authorName = todo.getAuthorName();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
    }

}
