package scheduledevelop.lv4.todo.dto.request;

public record TodoCreateRequestDto(Long userId, String username, String title, String contents ) {
}
