package scheduledevelop.lv4.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scheduledevelop.lv4.dto.tododto.TodoFindResponseDto;
import scheduledevelop.lv4.dto.tododto.TodoResponseDto;
import scheduledevelop.lv4.dto.tododto.TodosResponseDto;
import scheduledevelop.lv4.entity.Todo;
import scheduledevelop.lv4.entity.User;
import scheduledevelop.lv4.repository.TodoRepository;
import scheduledevelop.lv4.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // 1. todo CREATE
    @Override
    public TodoResponseDto createTodo(Long id, String username, String title, String contents) {

        // 1. 유저 검증 및 조회
        User findByIdFromCreateTodo = userRepository.findByUserIdorElseThrow(id);
        // 2. 생성 (repository에는 Todo 객체로 저장된다.)
        Todo newTodo = new Todo(username, title, contents);
        // 3. 연관관계 매핑
        newTodo.setUser(findByIdFromCreateTodo);
        // 4. 매핑된 객체(Todo)를 DB에 저장&반환
        return new TodoResponseDto(todoRepository.save(newTodo));
    }

    // 2. todo READ :: ALL
    @Override
    public List<TodosResponseDto> findTodos() {
        List<Todo> todoList = todoRepository.findAll();

        // Todo -> TodoResponseDto로 변환
        return todoList.stream().map(TodosResponseDto::new).collect(Collectors.toList());
    }

    // 3. todo READ :: FIND ID
    @Override
    public TodoFindResponseDto findById(Long id) {

        Todo findById = todoRepository.findByIdOrElseThrow(id);
        return new TodoFindResponseDto(findById);
    }

    // 4. todo MODIFY :: TITLE, CONTENTS
    @Override
    @Transactional // Dirty Checking 작동을 위한 어노테이션.
    public TodoResponseDto modifyTodo(Long id, String title, String contents) {

        Todo findByIdFromModifyTodo = todoRepository.findByIdOrElseThrow(id);
        findByIdFromModifyTodo.setTitle(title);
        findByIdFromModifyTodo.setContents(contents);

        return new TodoResponseDto(findByIdFromModifyTodo);
    }

    // 5. todo DELETE
    @Override
    public void deleteTodo(Long id) {

        todoRepository.findByIdOrElseThrow(id);
        todoRepository.deleteById(id);
    }
}
