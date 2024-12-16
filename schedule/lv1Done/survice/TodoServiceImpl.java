package schedule.lv1Done.survice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import schedule.lv1Done.TodoEntity;
import schedule.lv1Done.dto.TodoRequestDto;
import schedule.lv1Done.dto.TodoResponseDto;
import schedule.lv1Done.repository.TodoRepository;

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
    public void modify() {

    }

    @Override
    public void delete() {

    }
}
