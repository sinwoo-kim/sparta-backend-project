package scheduledevplus.lv2.todo.service;

import scheduledevplus.lv2.todo.dto.request.TodoCreateRequestDto;
import scheduledevplus.lv2.todo.dto.response.TodoCreateResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodoFindResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodoModifyResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodosResponseDto;

import java.util.List;

public interface TodoService {
    TodoCreateResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto);

    List<TodosResponseDto> findTodos();

    TodoFindResponseDto findById(Long id);

    TodoModifyResponseDto modifyTodo(Long id, String title, String contents);

    void deleteTodo(Long id);
}
