package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty(value="user_name", required=true,defaultValue = "Default UserName")
    String userName;
    String email;
    String password;
    String contact;


}