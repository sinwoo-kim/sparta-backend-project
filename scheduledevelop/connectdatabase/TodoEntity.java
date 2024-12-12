package scheduledevelop.connectdatabase;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class TodoEntity {
    private long id;
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

    public TodoEntity(long id, String work, String name, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.work = work;
        this.name = name;
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
