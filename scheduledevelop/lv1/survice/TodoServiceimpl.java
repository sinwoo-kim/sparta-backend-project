package scheduledevelop.lv1.survice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.TodoFindResponseDto;
import scheduledevelop.lv1.dto.TodoResponseDto;
import scheduledevelop.lv1.dto.TodosResponseDto;
import scheduledevelop.lv1.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;
    // 1. todo CREATE
    @Override
    public TodoResponseDto createTodo(String authorName, String title, String contents) {

        Todo todo = new Todo(authorName, title, contents);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(savedTodo);
    }
    // 2. todo READ :: ALL
    @Override
    public List<TodosResponseDto> findTodos() {
        List<Todo> todoList = todoRepository.findAll();

        // Todo -> TodoResponseDto로 변환
        return todoList.stream().map(TodosResponseDto::new).collect(Collectors.toList());
    }

    // 3. todo READ :: FIND ID
    @Override
    public TodoFindResponseDto findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + id));

        return new TodoFindResponseDto(todo);
    }
}
