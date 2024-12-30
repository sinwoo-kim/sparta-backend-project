package scheduledevplus.lv2.todo.dto.request;

import scheduledevplus.lv2.todo.dto.response.TodoCreateResponseDto;
import scheduledevplus.lv2.todo.entity.Todo;

public record TodoCreateRequestDto(Long userId, String username, String title, String contents ) {
}
