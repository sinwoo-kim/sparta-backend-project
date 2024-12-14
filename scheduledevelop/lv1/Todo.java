package scheduledevelop.lv1;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

@Getter
@Entity(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long id;

    @Column(nullable = false) // null 허용 안함
    private String TodoName;

    @Column(nullable = false)
    private String Title;

    @Column(columnDefinition = "longtext")
    private String contents;

}
