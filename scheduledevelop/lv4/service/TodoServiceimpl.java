package scheduledevelop.lv4.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import scheduledevelop.lv4.dto.tododto.*;
import scheduledevelop.lv4.entity.Todo;
import scheduledevelop.lv4.entity.User;
import scheduledevelop.lv4.repository.TodoRepository;
import scheduledevelop.lv4.repository.UserRepository;

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
    public TodoCreateResponseDto createTodo(TodoCreateRequestDto todoCreateRequestDto) {
        log.info("1. todoService.createTodo() 실행");

        // 1. 유저 ID 조회
        User findByIdFromCreateUserId = userRepository.findByIdOrElseThrow(todoCreateRequestDto.getUserId());

        // 2. 생성 (repository에는 Todo 객체로 저장된다.)
        Todo newTodo = Todo.createFromCreateTodoDto(todoCreateRequestDto);

        // 3. 연관관계 매핑
        newTodo.setUser(findByIdFromCreateUserId);
        log.info("3. newTodo = {}", newTodo);
        log.info("4. newTodo.getUser().getId = {}", newTodo.getUser().getUserId());

        // 4. 매핑된 객체를 DB에 저장&반환
        return new TodoCreateResponseDto(todoRepository.save(newTodo));
    }

    // 2. todo READ :: ALL
    @Override
    public List<TodosResponseDto> findTodos() {
        List<Todo> todoList = todoRepository.findAll();

        // Todo -> TodoResponseDto로 변환
        return todoList.stream().map(TodosResponseDto::new).collect(Collectors.toList());
    }

    // 3. todo READ :: SELECT
    @Override
    public TodoFindResponseDto findById(Long id) {

        Todo findById = todoRepository.findByIdOrElseThrow(id);
        return new TodoFindResponseDto(findById);
    }

    // 4. todo MODIFY :: TITLE, CONTENTS
    @Override
    @Transactional // Dirty Checking 작동을 위한 어노테이션.
    public TodoModifyResponseDto modifyTodo(Long id, String title, String contents) {

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
