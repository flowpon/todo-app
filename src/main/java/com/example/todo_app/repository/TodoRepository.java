package com.example.todo_app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull; // ← 追加
import org.springframework.stereotype.Repository;

import com.example.todo_app.model.Todo;

@Repository
public class TodoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 全てのTodoを取得
    public List<Todo> findAll() {
        String sql = "SELECT * FROM todos ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    // Todoを保存
    public void save(Todo todo) {
        String sql = "INSERT INTO todos (text, completed, created_at) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, todo.getText(), todo.isCompleted());
    }

    // Todoを更新
    public void update(Todo todo) {
        String sql = "UPDATE todos SET text = ?, completed = ? WHERE id = ?";
        jdbcTemplate.update(sql, todo.getText(), todo.isCompleted(), todo.getId());
    }

    // Todoを削除
    public void deleteById(Long id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // IDでTodoを検索
    public Todo findById(Long id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        List<Todo> todos = jdbcTemplate.query(sql, new TodoRowMapper(), id);
        return todos.isEmpty() ? null : todos.get(0);
    }

    // RowMapper
    private class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setText(rs.getString("text"));
            todo.setCompleted(rs.getBoolean("completed"));
            todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return todo;
        }
    }
}
