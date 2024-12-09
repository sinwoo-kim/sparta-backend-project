package schedule.lv1.controller.repository;

import schedule.lv1.controller.TodoEntity;
import schedule.lv1.controller.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
}
