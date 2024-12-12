package scheduledevelop.lv2.survice;

import scheduledevelop.lv2.dto.TodoRequestDto;
import scheduledevelop.lv2.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);
    TodoResponseDto deleteTodo(Long id);
}
