package scheduledevelop.lv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import scheduledevelop.lv2.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByUserIdorElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exit id =" + id));
    }
}
