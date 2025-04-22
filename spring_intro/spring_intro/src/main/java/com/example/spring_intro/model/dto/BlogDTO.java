package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlogDTO {

    @JsonProperty(value = "title",required = true,defaultValue = "Default Title")  //, access = JsonProperty.Access.WRITE_ONLY
    String title;
    @JsonProperty(value = "content",required = true,defaultValue = "Default Content")
    String content;
}

