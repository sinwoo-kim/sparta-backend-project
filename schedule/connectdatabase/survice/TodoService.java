package schedule.connectdatabase.survice;

import schedule.connectdatabase.dto.TodoRequestDto;
import schedule.connectdatabase.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);

    List<TodoResponseDto> findAllTodos();

    TodoResponseDto findSelectTodo(Long id);

    TodoResponseDto updateTodo(Long id, String work);

    void deleteTodo(Long id, int password);
}
