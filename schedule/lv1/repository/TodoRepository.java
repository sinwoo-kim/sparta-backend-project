package schedule.lv1.repository;

import schedule.lv1.TodoEntity;
import schedule.lv1.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
}
