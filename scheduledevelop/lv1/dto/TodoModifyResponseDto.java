package scheduledevelop.lv1.dto;

import lombok.Getter;
import scheduledevelop.lv1.Todo;

@Getter
public class TodoModifyResponseDto {
    private final String title;
    private final String contents;

    public TodoModifyResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
    }
}
