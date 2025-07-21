package com.example.todo_app.model;

import java.time.LocalDateTime;

public class Todo {
    private Long id;
    private String text;
    private boolean completed;
    private LocalDateTime createdAt;
    
    // デフォルトコンストラクタ
    public Todo() {
    }
    
    // テキストを受け取るコンストラクタ
    public Todo(String text) {
        this.text = text;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
    
    // すべてのフィールドを受け取るコンストラクタ
    public Todo(Long id, String text, boolean completed, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.completed = completed;
        this.createdAt = createdAt;
    }
    
    // Getter methods
    public Long getId() {
        return id;
    }
    
    public String getText() {
        return text;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    // Setter methods
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString method (デバッグ用)
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                '}';
    }
}