package com.example.spring_intro.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogShowDTO {
    String blogTitle;
    String content;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String author;
    List<String> comments;
}