package scheduledevelop.lv1.survice;

import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.TodoFindResponseDto;
import scheduledevelop.lv1.dto.TodoResponseDto;
import scheduledevelop.lv1.dto.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(String authorName, String title, String contents);
    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);
}
