package scheduledevelop.lv4.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import scheduledevelop.lv4.todo.dto.request.TodoCreateRequestDto;
import scheduledevelop.lv4.todo.dto.request.TodoModifyRequestDto;
import scheduledevelop.lv4.todo.dto.response.TodoCreateResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodoFindResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodoModifyResponseDto;
import scheduledevelop.lv4.todo.dto.response.TodosResponseDto;
import scheduledevelop.lv4.todo.service.TodoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor // 생성자 주입 로직 자동 생성
public class TodoController {

    private final TodoService todoService;

    // CREATE TODO
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(@RequestBody TodoCreateRequestDto requestDto) {
        log.info("createTodoAPi를 실행합니다.");
        TodoCreateResponseDto todoCreateResponseDto = todoService.createTodo(requestDto);
        return new ResponseEntity<>((todoCreateResponseDto), HttpStatus.CREATED);
    }

    // READ TODO ALL
    @GetMapping
    public List<TodosResponseDto> findTodosAPI() {
        return todoService.findTodos();
    }

    // REAL TODO SELECT
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoFindResponseDto> findTodoAPI(@PathVariable("todoId") Long id) {
        TodoFindResponseDto findTodo = todoService.findById(id);
        return new ResponseEntity<>(findTodo, HttpStatus.OK);
    }

    // MODIFIY TODO ( Title, Contents )
    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoModifyResponseDto> modifyTodoAPI(@PathVariable("todoId") Long id, @RequestBody TodoModifyRequestDto requestDto) {
        return new ResponseEntity<>(todoService.modifyTodo(id, requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }


    // DELETE TODO SELECT
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoAPI(@PathVariable("todoId") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deletion successful");
    }


}
