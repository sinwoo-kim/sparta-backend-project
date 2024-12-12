package scheduledevelop.lv1.repository;

import org.springframework.stereotype.Repository;
import scheduledevelop.lv1.TodoEntity;
import scheduledevelop.lv1.dto.TodoResponseDto;

import java.util.*;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private static Map<Long, TodoEntity> myTodoRepository = new HashMap<>();

    @Override
    public TodoEntity save(TodoEntity todo) {
        Long todoId = myTodoRepository.isEmpty() ? 1 : Collections.max(myTodoRepository.keySet()) + 1;
        todo.setId(todoId);
        myTodoRepository.put(todoId, todo);
        return todo;
    }

    @Override
    public TodoEntity findSelectTodo(Long id) {
        return myTodoRepository.get(id);
    }

    @Override
    public List<TodoResponseDto> findAllTodos() {

        List<TodoResponseDto> findAllTodos = new ArrayList<>();
        // HashMap<Memo> -> List<MemoResponseDto>
        for(TodoEntity todo : myTodoRepository.values()) {
            TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
            findAllTodos.add(todoResponseDto);
        }

        return findAllTodos;
    }
}
