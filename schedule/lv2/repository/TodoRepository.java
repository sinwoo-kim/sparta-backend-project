package schedule.lv2.repository;

import schedule.lv2.TodoEntity;
import schedule.lv2.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
    TodoEntity updateTodo(Long id);
    TodoEntity deleteTodo(Long id);
}
