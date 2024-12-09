package schedule.survice;

import schedule.TodoEntity;
import schedule.dto.TodoRequestDto;
import schedule.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    void modify();
    void delete();
}
