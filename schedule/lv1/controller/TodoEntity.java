package schedule.lv1.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
public class TodoEntity {
@Setter
    private Long id;
    private int work;
    private String name;
    private int password;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public TodoEntity(long id, int work, String name, int password, LocalDateTime writeDate, LocalDateTime modifyDate) {
        this.id = id;
        this.work = work;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public TodoEntity(int work, String name, int password, LocalDateTime writeDate, LocalDateTime modifyDate) {
        this.work = work;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

}
