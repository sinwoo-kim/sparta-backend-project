package schedule.lv1.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schedule.lv1.controller.dto.TodoRequestDto;
import schedule.lv1.controller.dto.TodoResponseDto;
import schedule.lv1.controller.survice.TodoService;
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

    // 일정 생성
    @PostMapping()
    public ResponseEntity<TodoResponseDto> createTodoAPI(@RequestBody TodoRequestDto requestDto) {
        return new ResponseEntity<>(todoService.createTodo(requestDto), HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping()
    public List<TodoResponseDto> findAllTodoAPI() {

        return todoService.findAllTodos();
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findSelectTodoAPI(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.findSelectTodo(id), HttpStatus.OK );
    }

}
