package scheduledevelop.lv1.survice;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.*;
import scheduledevelop.lv1.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceimpl implements TodoService {

    private final TodoRepository todoRepository;
    // 1. todo CREATE
    @Override
    public TodoResponseDto createTodo(String authorName, String title, String contents) {

        Todo todo = new Todo(authorName, title, contents);
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
        Optional<Todo> byId = todoRepository.findById(id);

        if(byId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not found by id" + id);
        }
        return new TodoFindResponseDto(byId.get());
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
