package com.example.todobackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String text;
    private Boolean completed = false;
    private LocalDateTime deadline;
    private String priority = "Low";
}
