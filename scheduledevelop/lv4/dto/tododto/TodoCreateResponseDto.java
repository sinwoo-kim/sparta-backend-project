package scheduledevelop.lv4.dto.tododto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import scheduledevelop.lv4.entity.Todo;

@Getter
public class TodoCreateResponseDto {

    private final Long todoId;
    private final String username;
    private final String title;
    private final String contents;
    private final Long userId;

    public TodoCreateResponseDto(Todo todo) {
        this.todoId = todo.getId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userId = todo.getUser().getId();
    }

}