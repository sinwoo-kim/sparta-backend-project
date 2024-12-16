package scheduledevelop.lv1.dto;

import lombok.Getter;
import scheduledevelop.lv1.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoFindResponseDto {

    private final Long id;
    private final String authorName;
    private final String title;
    private final String contents;
    private LocalDateTime createAt;    // 생성 날짜
    private LocalDateTime modifiedAt;  // 수정 날짜

    public TodoFindResponseDto(Todo todo) {
        this.id = todo.getId();
        this.authorName = todo.getAuthorName();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createAt = todo.getCreateAt();        // BaseEntity에서 상속된 필드
        this.modifiedAt = todo.getModifiedAt();
    }

}