package com.example.todobackend.dto;

import com.example.todobackend.config.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String text;
    private Boolean completed = false;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deadline;

    private String priority = "Low";
}