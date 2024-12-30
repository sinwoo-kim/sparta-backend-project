package scheduledevplus.lv2.todo.dto.response;

import lombok.Getter;
import scheduledevplus.lv2.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoFindResponseDto {

    private final Long todoId;
    private final String username;
    private final String title;
    private final String contents;
    private LocalDateTime createAt;    // 생성 날짜
    private LocalDateTime modifiedAt;  // 수정 날짜

    public TodoFindResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.username = todo.getUsername();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createAt = todo.getCreateAt();        // BaseEntity에서 상속된 필드
        this.modifiedAt = todo.getModifiedAt();
    }

}