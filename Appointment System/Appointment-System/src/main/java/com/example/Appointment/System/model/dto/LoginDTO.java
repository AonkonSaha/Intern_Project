package com.example.Appointment.System.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
