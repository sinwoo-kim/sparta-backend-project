package scheduledevplus.lv2.todo.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scheduledevplus.lv2.todo.dto.request.TodoCreateRequestDto;
import scheduledevplus.lv2.todo.dto.response.TodoCreateResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodoFindResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodoModifyResponseDto;
import scheduledevplus.lv2.todo.dto.response.TodosResponseDto;
import scheduledevplus.lv2.todo.entity.Todo;
import scheduledevplus.lv2.todo.repository.TodoRepository;
import scheduledevplus.lv2.user.entity.User;
import scheduledevplus.lv2.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    // 1. todo CREATE
    @Override
    public TodoCreateResponseDto createTodo(
            TodoCreateRequestDto todoCreateRequestDto
    ) {
        log.info("1. todoService.createTodo() 실행");
        User foundUser = userRepository.findByIdOrElseThrow(todoCreateRequestDto.userId());

        Todo newTodo = Todo.create(
                foundUser,
                todoCreateRequestDto.username(),
                todoCreateRequestDto.title(),
                todoCreateRequestDto.contents()
        );

        Todo savedTodo = todoRepository.save(newTodo);
        return TodoCreateResponseDto.toDto(savedTodo);
    }

    // 2. todo READ :: ALL
    @Override
    public List<TodosResponseDto> findTodos() {

        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(TodosResponseDto::new).collect(Collectors.toList());
    }

    // 3. todo READ :: SELECT
    @Override
    public TodoFindResponseDto findById(Long todoId) {

        Todo foundTodo = todoRepository.findByIdOrElseThrow(todoId);
        return new TodoFindResponseDto(foundTodo);
    }

    // 4. todo MODIFY :: TITLE, CONTENTS
    @Override
    @Transactional // Dirty Checking 작동을 위한 어노테이션.
    public TodoModifyResponseDto modifyTodo(
            Long id,
            String title,
            String contents
    ) {

        Todo findByIdFromModifyTodo = todoRepository.findByIdOrElseThrow(id);
        findByIdFromModifyTodo.setTitle(title);
        findByIdFromModifyTodo.setContents(contents);

        return new TodoModifyResponseDto(findByIdFromModifyTodo);
    }

    // 5. todo DELETE
    @Override
    public void deleteTodo(Long id) {

        todoRepository.findByIdOrElseThrow(id);
        todoRepository.deleteById(id);
    }
}
