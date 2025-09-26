package com.example.todobackend.service;

import com.example.todobackend.dto.TodoRequest;
import com.example.todobackend.dto.TodoResponse;
import com.example.todobackend.entity.Todo;
import com.example.todobackend.entity.User;
import com.example.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponse createTodo(User user, TodoRequest request) {
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setText(request.getText());
        todo.setCompleted(request.getCompleted());
        todo.setDeadline(request.getDeadline());
        todo.setPriority(request.getPriority());
        Todo saved = todoRepository.save(todo);
        return toResponse(saved);
    }

    public List<TodoResponse> getTodos(User user) {
        return todoRepository.findByUser(user).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TodoResponse updateTodo(Long id, User user, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        todo.setText(request.getText());
        todo.setCompleted(request.getCompleted());
        todo.setDeadline(request.getDeadline());
        todo.setPriority(request.getPriority());
        Todo updated = todoRepository.save(todo);
        return toResponse(updated);
    }

    public void deleteTodo(Long id, User user) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        todoRepository.delete(todo);
    }

    // ✅ Xóa nhiều todo
    public void deleteTodos(List<Long> ids, User user) {
        List<Todo> todos = todoRepository.findAllById(ids);
        for (Todo todo : todos) {
            if (!todo.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("Unauthorized to delete todo with id: " + todo.getId());
            }
        }
        todoRepository.deleteAll(todos);
    }

    private TodoResponse toResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getText(),
                todo.getCompleted(),
                todo.getCreatedAt(),
                todo.getDeadline(),
                todo.getPriority()
        );
    }
}
