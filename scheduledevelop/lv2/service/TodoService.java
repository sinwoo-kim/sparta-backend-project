package scheduledevelop.lv2.service;

import scheduledevelop.lv2.dto.tododto.TodoFindResponseDto;
import scheduledevelop.lv2.dto.tododto.TodoResponseDto;
import scheduledevelop.lv2.dto.tododto.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(Long id, String authorName, String title, String contents);
    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
