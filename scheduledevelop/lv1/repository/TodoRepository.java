package scheduledevelop.lv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import scheduledevelop.lv1.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
