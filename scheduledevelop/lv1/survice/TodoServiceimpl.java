package scheduledevelop.lv1.survice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.TodoRequestDto;
import scheduledevelop.lv1.dto.TodoResponseDto;
import scheduledevelop.lv1.repository.TodoRepository;

@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoResponseDto createTodo(String authorName, String title, String contents) {

        Todo todo = new Todo(authorName, title, contents);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(savedTodo);
    }
}
