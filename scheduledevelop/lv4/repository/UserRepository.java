package scheduledevelop.lv4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import scheduledevelop.lv4.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * RUD를 위한 User 테이블 조회 메서드
     *
     * @param id
     * @return
     */
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalStateException("User not found for ID: " + id));
    }

    /**
     * 로그인 email & paswword 확인 로직 from UserService.login()
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found for email"));
    }
}