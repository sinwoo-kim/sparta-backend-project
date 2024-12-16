package schedule.lv1Done.survice;

import schedule.lv1Done.dto.TodoRequestDto;
import schedule.lv1Done.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todoRequestDto);
    List<TodoResponseDto> findAllTodos();
    TodoResponseDto findSelectTodo(Long id);
    void modify();
    void delete();
}
