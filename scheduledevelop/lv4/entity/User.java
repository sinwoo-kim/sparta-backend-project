package scheduledevelop.lv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import scheduledevelop.lv4.dto.userdto.LoginRequestDto;
import scheduledevelop.lv4.dto.userdto.LoginResponseDto;
import scheduledevelop.lv4.dto.userdto.SignUpRequestDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @CreatedDate
    private LocalDateTime createAt;

    private User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    // signUp dto -> User Entity
    public static User createFromSignUpDto(SignUpRequestDto signUpRequestDto) {
        return new User(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getUsername());
    }

    // login dto -> User Entity
    public static User toEntityFromLoginRequestDto(LoginRequestDto loginRequestDto) {
        return new User(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

}
