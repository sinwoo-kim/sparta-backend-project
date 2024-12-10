package schedule.connectdatabase.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import schedule.connectdatabase.dto.TodoRequestDto;
import schedule.connectdatabase.dto.TodoResponseDto;
import schedule.connectdatabase.survice.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final ApplicationContext applicationContext;
    private final TodoService todoService;

    public TodoController(ApplicationContext applicationContext, TodoService todoService) {
        this.applicationContext = applicationContext;
        this.todoService = todoService;
    }

    // 1. 일정 생성
    @PostMapping()
    public ResponseEntity<TodoResponseDto> createTodoAPI(@RequestBody TodoRequestDto requestDto) {
        return new ResponseEntity<>(todoService.createTodo(requestDto), HttpStatus.CREATED);
    }

    // 2. 전체 일정 조회
    @GetMapping()
    public List<TodoResponseDto> findAllTodoAPI() {

        return todoService.findAllTodos();
    }

    // 3. 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findSelectTodoAPI(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.findSelectTodo(id), HttpStatus.OK );
    }

    // 4. 선택 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodoAPI(@PathVariable("id") Long id,
                                                         @RequestBody TodoRequestDto requestDto) {

        return new ResponseEntity<>(todoService.updateTodo(id, requestDto.getWork()), HttpStatus.OK);
    }

    // 5. 선택 일정 삭제
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoAPI(@PathVariable("id") Long id,
                                                @RequestBody TodoRequestDto requestDto) {

        todoService.deleteTodo(id, requestDto.getPassword());
        return new ResponseEntity<>("Todo successfully deleted with id: ", HttpStatus.OK);
    }

}
