package scheduledevelop.lv4.todo.service;

import scheduledevelop.lv4.todo.dto.request.TodoCreateRequestDto;
import scheduledevelop.lv4.todo.dto.response.TodoCreateResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodoFindResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodoModifyResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoCreateResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto);

    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoModifyResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
