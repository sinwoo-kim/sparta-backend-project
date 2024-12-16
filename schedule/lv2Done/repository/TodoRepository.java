package schedule.lv2Done.repository;

import schedule.lv2Done.TodoEntity;
import schedule.lv2Done.dto.TodoResponseDto;

import java.util.List;

public interface TodoRepository {
    TodoEntity save(TodoEntity todo);
    TodoEntity findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
    TodoEntity updateTodo(Long id);
    TodoEntity deleteTodo(Long id);
}
