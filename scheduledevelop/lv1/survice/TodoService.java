package scheduledevelop.lv1.survice;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.TodoRequestDto;
import scheduledevelop.lv1.dto.TodoResponseDto;
import scheduledevelop.lv1.repository.TodoRepository;

public interface TodoService {
    TodoResponseDto createTodo(String authorName, String title, String contents);
}
