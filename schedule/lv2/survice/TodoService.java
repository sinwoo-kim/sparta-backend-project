package schedule.lv2.survice;

import schedule.lv2.dto.TodoRequestDto;
import schedule.lv2.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    TodoResponseDto updateTodo(Long id, TodoRequestDto dto);
    void delete();
}
