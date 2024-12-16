package scheduledevelop.lv1.survice;

import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.*;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(String authorName, String title, String contents);
    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
