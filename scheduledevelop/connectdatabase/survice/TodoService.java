package scheduledevelop.connectdatabase.survice;

import scheduledevelop.connectdatabase.dto.TodoRequestDto;
import scheduledevelop.connectdatabase.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);

    List<TodoResponseDto> findAllTodos();

    TodoResponseDto findSelectTodo(Long id);

    TodoResponseDto updateTodo(Long id, String work);

    void deleteTodo(Long id, int password);
}
