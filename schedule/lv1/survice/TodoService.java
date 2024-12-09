package schedule.lv1.survice;

import schedule.lv1.dto.TodoRequestDto;
import schedule.lv1.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    void modify();
    void delete();
}
