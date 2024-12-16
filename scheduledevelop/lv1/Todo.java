package scheduledevelop.lv1;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import scheduledevelop.lv1.dto.TodoRequestDto;

@Getter
@Entity(name = "schedule")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long id;

    @Column(nullable = false, unique = true) // null 허용 안함
    private String authorName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Todo(String authorName, String title, String contents) {
        this.authorName = authorName;
        this.title = title;
        this.contents = contents;
    }

    protected Todo() {}

}
