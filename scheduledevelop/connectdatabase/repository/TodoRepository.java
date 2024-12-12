package scheduledevelop.connectdatabase.repository;

import scheduledevelop.connectdatabase.TodoEntity;
import scheduledevelop.connectdatabase.dto.TodoResponseDto;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto save(TodoEntity todo);

    Optional<TodoEntity> findSelectTodo(Long id);

    List<TodoResponseDto> findAllTodos();

    int updateTodo(Long id, String work);

    int deleteTodo(Long id);
}
