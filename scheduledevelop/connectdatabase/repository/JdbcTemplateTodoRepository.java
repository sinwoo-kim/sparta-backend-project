package scheduledevelop.connectdatabase.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import scheduledevelop.connectdatabase.TodoEntity;
import scheduledevelop.connectdatabase.dto.TodoResponseDto;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateTodoRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    //    private final TodoApplication todoApplication;
    public JdbcTemplateTodoRepository(DataSource dataSourse) {
        this.jdbcTemplate = new JdbcTemplate(dataSourse);
    }

    // Todo CREATE
    // TodoEntity를 데이터베이스에 저장하는 메서드
    @Override
    public TodoResponseDto save(TodoEntity todo) {
        // INSERT QUERY 문자열 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Todo").usingGeneratedKeyColumns("id");

        // INSERT에 사용될 파라미터 맵 생성
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("work", todo.getWork());
        parameters.put("name", todo.getName());
        parameters.put("password", todo.getPassword());
        parameters.put("createdAt", todo.getCreatedAt());
        parameters.put("updateAt", todo.getUpdateAt());
        // 저장 후 생성된 key값 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoResponseDto(key.longValue(), todo.getWork(), todo.getName(), todo.getCreatedAt(), todo.getUpdateAt());
    }

    // Todo ALL 조회
    @Override
    public List<TodoResponseDto> findAllTodos() {
        // SQL 쿼리를 실행하여 결과를 TodoResponseDto 객체 리스트로 매핑
        return jdbcTemplate.query("select * from todo", todoRowMapper());
    }

    // Todo 선택 조회
    @Override
    public Optional<TodoEntity> findSelectTodo(Long id) {
        List<TodoEntity> result = jdbcTemplate.query("SELECT * FROM todo WHERE id = ?", todoRowMapperV2(), id);
        return result.stream().findAny();
    }

    // Todo UPDATE
    @Override
    public int updateTodo(Long id, String work) {
        return jdbcTemplate.update("update todo set work = ? where id = ?", work, id);
    }

    // Todo DELETE
    @Override
    public int deleteTodo(Long id) {
        return jdbcTemplate.update("delete from todo where id = ?", id);
    }

    // RowMapper를 사용하여 ResultSet의 각 행을 TodoResponseDto로 변환
    private RowMapper<TodoResponseDto> todoRowMapper() {
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("work"),
                        rs.getString("name"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<TodoEntity> todoRowMapperV2() {
        return new RowMapper<TodoEntity>() {
            @Override
            public TodoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoEntity(
                        rs.getLong("id"),
                        rs.getString("work"),
                        rs.getString("name"),
                        rs.getInt("password"), // 비밀번호 가져오는 로직이 없었음.
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updateAt").toLocalDateTime()
                );
            }
        };
    }

}
