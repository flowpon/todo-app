package com.example.todo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    
    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = todoRepository.findAll();
        
        // 統計情報を計算
        int totalTodos = todos.size();
        int completedTodos = (int) todos.stream().filter(Todo::isCompleted).count();
        int pendingTodos = totalTodos - completedTodos;
        
        model.addAttribute("todos", todos);
        model.addAttribute("totalTodos", totalTodos);
        model.addAttribute("completedTodos", completedTodos);
        model.addAttribute("pendingTodos", pendingTodos);
        
        return "index";
    }
    
    @PostMapping("/add")
    public String addTodo(@RequestParam String text) {
        if (text != null && !text.trim().isEmpty()) {
            Todo todo = new Todo(text.trim());
            todoRepository.save(todo);
        }
        return "redirect:/";
    }
    
    @PostMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
            todoRepository.update(todo);
        }
        return "redirect:/";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}
