package com.example.crud_intern.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserCommentDTO {

     @JsonProperty(value = "user_comment",required = true,defaultValue = "Default Content")
     String content;
}
