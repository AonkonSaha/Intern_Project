package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCommentDTO {

    @JsonProperty(value = "user_comment",required = true,defaultValue = "Default Content")
    String content;
    @JsonProperty(value = "blog_name")
    String blogName;
    @JsonProperty(value = "author")
    String author;
}

