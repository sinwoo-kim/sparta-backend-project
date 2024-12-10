package schedule.connectdatabase.repository;

import schedule.connectdatabase.TodoEntity;
import schedule.connectdatabase.dto.TodoRequestDto;
import schedule.connectdatabase.dto.TodoResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto save(TodoEntity todo);
    Optional<TodoEntity> findSelectTodo(Long id);
    List<TodoResponseDto> findAllTodos();
    int updateTodo(Long id, String work);
    int deleteTodo(Long id);
}
