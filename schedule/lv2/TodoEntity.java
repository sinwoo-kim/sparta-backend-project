package schedule.lv2;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
public class TodoEntity {
@Setter
    private Long id;
    private String work;
    private String name;
    private int password;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public TodoEntity(long id, String work, String name, int password, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.work = work;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public TodoEntity(String work, String name, int password, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.work = work;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public void update(String work, String name, LocalDateTime updateAt) {
        this.work = work;
        this.name = name;
        this.updateAt = updateAt;
    }
}
