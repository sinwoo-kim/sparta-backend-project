package schedule.lv1.controller.survice;

import schedule.lv1.controller.dto.TodoRequestDto;
import schedule.lv1.controller.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    void modify();
    void delete();
}
