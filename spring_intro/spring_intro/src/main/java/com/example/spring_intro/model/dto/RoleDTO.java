package com.example.spring_intro.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data

public class RoleDTO {
    @JsonProperty(value = "role",required = true)
    private String role;
    @JsonProperty(value = "user_id",required = true)
    private List<Long> userId;

}

