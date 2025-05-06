package com.example.Appointment.System.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty(value = "user_name")
    private String username;
    private String password;
}
