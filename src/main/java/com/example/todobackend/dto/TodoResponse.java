package com.example.todobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String text;
    private Boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private String priority;
}
