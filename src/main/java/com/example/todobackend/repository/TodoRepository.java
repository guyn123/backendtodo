package com.example.todobackend.repository;

import com.example.todobackend.entity.Todo;
import com.example.todobackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
