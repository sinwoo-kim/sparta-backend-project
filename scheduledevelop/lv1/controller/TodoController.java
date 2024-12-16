package scheduledevelop.lv1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.*;
import scheduledevelop.lv1.survice.TodoService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor // 생성자 주입 로직 자동 생성
public class TodoController {

    private final TodoService todoService;


    // 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodoAPI(@RequestBody TodoRequestDto requestDto) {
        log.info("createTodoAPi를 실행합니다.");
        TodoResponseDto todoResponseDto = todoService.createTodo(requestDto.getAuthorName(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>((todoResponseDto), HttpStatus.CREATED);
    }

     // 전체 조회
    @GetMapping
    public List<TodosResponseDto> findTodosAPI() {
        return todoService.findTodos();
    }

    // 선택 조회
    @GetMapping("/{id}")
    public ResponseEntity<TodoFindResponseDto> findTodoAPI(@PathVariable("id") Long id) {
        TodoFindResponseDto findTodo = todoService.findById(id);
        return new ResponseEntity<>(findTodo, HttpStatus.OK);
    }

    // 선택 수정 ( Title, Contents )
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> modifyTodoAPI(@PathVariable("id") Long id, @RequestBody TodoRequestDto requestDto) {
        return new ResponseEntity<>(todoService.modifyTodo(id, requestDto.getTitle(),requestDto.getContents()), HttpStatus.OK);
    }



}
