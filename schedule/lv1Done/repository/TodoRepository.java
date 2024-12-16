package schedule.lv1Done.repository;

import schedule.lv1Done.TodoEntity;
import schedule.lv1Done.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
}
