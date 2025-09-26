package com.example.todobackend.controller;

import com.example.todobackend.dto.TodoRequest;
import com.example.todobackend.dto.TodoResponse;
import com.example.todobackend.entity.User;
import com.example.todobackend.service.TodoService;
import com.example.todobackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping
    public List<TodoResponse> getTodos(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        return todoService.getTodos(user);
    }

    @PostMapping
    public TodoResponse createTodo(Authentication authentication, @RequestBody TodoRequest request) {
        User user = userService.getByEmail(authentication.getName());
        return todoService.createTodo(user, request);
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(Authentication authentication, @PathVariable Long id, @RequestBody TodoRequest request) {
        User user = userService.getByEmail(authentication.getName());
        return todoService.updateTodo(id, user, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(Authentication authentication, @PathVariable Long id) {
        User user = userService.getByEmail(authentication.getName());
        todoService.deleteTodo(id, user);
    }

    // ✅ Xóa nhiều todo
    @DeleteMapping
    public void deleteTodos(Authentication authentication, @RequestBody List<Long> ids) {
        User user = userService.getByEmail(authentication.getName());
        todoService.deleteTodos(ids, user);
    }
}
