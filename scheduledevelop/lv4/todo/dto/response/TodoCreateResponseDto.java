package scheduledevelop.lv4.todo.dto.response;

import lombok.Getter;
import scheduledevelop.lv4.todo.entity.Todo;

@Getter
public class TodoCreateResponseDto {

    private final Long todoId;
    private final String username;
    private final String title;
    private final String contents;
    private final Long userId;

    public TodoCreateResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userId = todo.getUser().getUserId();
    }

}