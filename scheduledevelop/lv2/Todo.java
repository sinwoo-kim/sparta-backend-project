package scheduledevelop.lv2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long id;


    @Column(nullable = false, unique = true) // null 허용 안함
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Todo(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Todo() {
    }

}
