package scheduledevelop.lv2.dto;

import lombok.Getter;
import scheduledevelop.lv2.Todo;

import java.time.LocalDateTime;

@Getter
public class TodosResponseDto {

    private final Long id;
    private final String authorName;
    private final String title;
    private final String contents;
    private LocalDateTime createAt;    // 생성 날짜
    private LocalDateTime modifiedAt;  // 수정 날짜

    public TodosResponseDto(Todo todo) {
        this.id = todo.getId();
        this.authorName = todo.getAuthorName();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createAt = todo.getCreateAt();        // BaseEntity에서 상속된 필드
        this.modifiedAt = todo.getModifiedAt();
    }

}