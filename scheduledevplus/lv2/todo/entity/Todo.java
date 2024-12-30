package scheduledevplus.lv2.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import scheduledevplus.lv2.common.BaseEntity;
import scheduledevplus.lv2.todo.dto.request.TodoCreateRequestDto;
import scheduledevplus.lv2.user.entity.User;

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



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // findByIdFromCreateUserId를 심어준다.

    protected Todo() {
    }

    private Todo(User user, String username, String title, String contents) {
        this.user = user;
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public static Todo create(User user, String username, String title, String contents) {
        return new Todo(user, username, title, contents);
    }
}
