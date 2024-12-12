package scheduledevelop.lv1.repository;

import scheduledevelop.lv1.TodoEntity;
import scheduledevelop.lv1.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
}
