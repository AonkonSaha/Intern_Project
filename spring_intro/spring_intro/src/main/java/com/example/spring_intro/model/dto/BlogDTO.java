package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "title",required = true,defaultValue = "Default Title")  //, access = JsonProperty.Access.WRITE_ONLY
    String title;

    @JsonProperty(value = "content",required = true,defaultValue = "Default Content")
    String content;

    @JsonProperty(namespace = "author_user_id")
    private Long authorUserId;

    @JsonProperty(namespace = "created_at")
    private Long createdAt;

    @JsonProperty(namespace = "updated_att")
    private Long updatedAt;

    @JsonProperty(namespace = "rating")
    private Double rating;
}

