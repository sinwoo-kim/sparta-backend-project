package schedule.repository;

import schedule.TodoEntity;
import schedule.dto.TodoRequestDto;
import schedule.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
}
