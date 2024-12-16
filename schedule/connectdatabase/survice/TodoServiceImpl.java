package schedule.connectdatabase.survice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import schedule.connectdatabase.TodoEntity;
import schedule.connectdatabase.dto.TodoRequestDto;
import schedule.connectdatabase.dto.TodoResponseDto;
import schedule.connectdatabase.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 1. todo 생성
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

        return todoRepository.save(todo);
    }

    // 2. todo 전체 조회
    @Override
    public List<TodoResponseDto> findAllTodos() {
        return todoRepository.findAllTodos();
    }

    // 3. todo 선택 조회
    @Override
    public TodoResponseDto findSelectTodo(Long id) {

        Optional<TodoEntity> optionTodo = todoRepository.findSelectTodo(id);

        if (optionTodo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        return new TodoResponseDto(optionTodo.get());
    }

    // 4. todo 선택 수정
    @Transactional
    @Override
    public TodoResponseDto updateTodo(Long id, String work) {
        int updateRow = todoRepository.updateTodo(id, work);
        Optional<TodoEntity> optionalTodo = todoRepository.findSelectTodo(id);
        return new TodoResponseDto(optionalTodo.get());
    }

    // 5. todo 삭제
    @Override
    public void deleteTodo(Long id, int password) {
        Optional<TodoEntity> optionalTodo = todoRepository.findSelectTodo(id);

        // 삭제된 row가 0개 라면
        if (optionalTodo.isEmpty()) {
            log.error("Todo not found with id: {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found with id: " + id);
        }

        // 비밀번호 검증
        if (optionalTodo.get().getPassword() != password) {
            log.info("password: {}", optionalTodo.get());
            log.warn("Password does not match for id: {}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        log.error("Todo not found with id: {}", id);
        todoRepository.deleteTodo(id);
    }
}
