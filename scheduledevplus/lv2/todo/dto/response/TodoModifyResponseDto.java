package scheduledevplus.lv2.todo.dto.response;

import lombok.Getter;
import scheduledevplus.lv2.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoModifyResponseDto {

    private final Long todoId;
    private final String username;
    private final String title;
    private final String contents;
    private final LocalDateTime modifiedAt;

    public TodoModifyResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.modifiedAt = todo.getModifiedAt();
    }

}
