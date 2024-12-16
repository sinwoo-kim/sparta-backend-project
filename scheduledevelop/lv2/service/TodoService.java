package scheduledevelop.lv2.service;

import scheduledevelop.lv2.dto.TodoFindResponseDto;
import scheduledevelop.lv2.dto.TodoResponseDto;
import scheduledevelop.lv2.dto.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto createTodo(String authorName, String title, String contents);
    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
