package scheduledevelop.lv2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scheduledevelop.lv2.Todo;
import scheduledevelop.lv2.User;
import scheduledevelop.lv2.dto.tododto.TodoFindResponseDto;
import scheduledevelop.lv2.dto.tododto.TodoResponseDto;
import scheduledevelop.lv2.dto.tododto.TodosResponseDto;
import scheduledevelop.lv2.repository.TodoRepository;
import scheduledevelop.lv2.repository.UserRepository;


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

        User findUser = userRepository.findByIdorElseThrow(id);

        Todo todo = new Todo(username, title, contents);
        todo.setUser(findUser);

        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponseDto(savedTodo);
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
//        Optional<Todo> byId = todoRepository.findById(id);
//
//        if(byId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not found by id" + id);
//        }
        Todo byId = todoRepository.findByIdOrElseThrow(id);
        return new TodoFindResponseDto(byId);
    }

    // 4. todo MODIFY :: TITLE, CONTENTS
    @Override
    @Transactional // Dirty Checking 작동을 위한 어노테이션.
    public TodoResponseDto modifyTodo(Long id, String title, String contents) {

        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        findTodo.setTitle(title);
        findTodo.setContents(contents);

        return new TodoResponseDto(findTodo);
    }

    // 5. todo DELETE
    @Override
    public void deleteTodo(Long id) {

        Todo findTodo = todoRepository.findByIdOrElseThrow(id);
        todoRepository.deleteById(id);
    }
}
