package schedule.lv2Done.survice;

import schedule.lv2Done.dto.TodoRequestDto;
import schedule.lv2Done.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);
    TodoResponseDto deleteTodo(Long id);
}
