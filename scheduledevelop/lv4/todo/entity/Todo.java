package scheduledevelop.lv4.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import scheduledevelop.lv4.common.BaseEntity;
import scheduledevelop.lv4.todo.dto.request.TodoCreateRequestDto;
import scheduledevelop.lv4.user.entity.User;

@Getter
@Setter
@Entity(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long todoId;

    @Column(nullable = false, unique = true) // null 허용 안함
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    private Todo(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // findByIdFromCreateUserId를 심어준다.

    protected Todo() {
    }

    public static Todo createFromCreateTodoDto(TodoCreateRequestDto todoCreateRequestDto) {
        return new Todo(todoCreateRequestDto.username(),todoCreateRequestDto.title(),todoCreateRequestDto.contents());
    }
}
