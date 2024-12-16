package scheduledevelop.lv1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduledevelop.lv1.Todo;
import scheduledevelop.lv1.dto.TodoRequestDto;
import scheduledevelop.lv1.dto.TodoResponseDto;
import scheduledevelop.lv1.survice.TodoService;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor // 생성자 주입 로직 자동 생성
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodoAPI(@RequestBody TodoRequestDto requestDto) {
        log.info("createTodoAPi를 실행합니다.");
        TodoResponseDto todoResponseDto = todoService.createTodo(requestDto.getAuthorName(), requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>((todoResponseDto), HttpStatus.CREATED);
    }


}
