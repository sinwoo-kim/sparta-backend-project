package schedule.lv2.survice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import schedule.lv2.TodoEntity;
import schedule.lv2.dto.TodoRequestDto;
import schedule.lv2.dto.TodoResponseDto;
import schedule.lv2.repository.TodoRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    // 일정 생성
    @Override
    public TodoResponseDto createTodo(TodoRequestDto dto) {
        // DTO → Entity 변환 (객체 초기화)
        // 서비스 레이어에서 변환 책임을 가지고 있다.
        TodoEntity todo = new TodoEntity(
                dto.getWork(),
                dto.getName(),
                dto.getPassword(),
                dto.getCreatedAt(),
                dto.getUpdateAt());
        // 레포지토리에 저장 위임
        TodoEntity savedTodo = todoRepository.save(todo);
        // Dto로 반환
        return new TodoResponseDto(savedTodo);
    }
    // 전체 일정 조회
    @Override
    public List<TodoResponseDto> findAllTodos() {
        return todoRepository.findAllTodos();
    }

    // 단건 조회
    @Override
    public TodoResponseDto findSelectTodo(Long id) {

        TodoEntity todo = todoRepository.findSelectTodo(id);

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        return new TodoResponseDto(todo);
    }

    @Override
    public TodoResponseDto updateTodo(Long id, TodoRequestDto dto) {
        TodoEntity todo = todoRepository.updateTodo(id);

        if(todo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Does not exit id =" + id);
        }

        if(todo.getPassword() != dto.getPassword()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password mismatch");
        }
        todo.update(dto.getWork(), dto.getName(),dto.getUpdateAt());
        return new TodoResponseDto(todo);
    }
    @Override
    public TodoResponseDto deleteTodo(Long id) {
        return new TodoResponseDto(todoRepository.deleteTodo(id));

    }
}
