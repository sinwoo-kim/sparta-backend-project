package scheduledevelop.lv1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import scheduledevelop.lv1.TodoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private Long id;
    private int work;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateAt;

    public TodoResponseDto(TodoEntity todo) {
        this.id = todo.getId();
        this.work = todo.getWork();
        this.name = todo.getName();
        this.createdAt = todo.getCreatedAt();
        this.updateAt = todo.getUpdateAt();
    }
}
