package scheduledevelop.lv4.service;

import scheduledevelop.lv4.dto.tododto.*;

import java.util.List;

public interface TodoService {
    TodoCreateResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto);

    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoModifyResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
