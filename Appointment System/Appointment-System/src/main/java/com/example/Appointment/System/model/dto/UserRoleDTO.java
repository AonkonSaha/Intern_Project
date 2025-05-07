package com.example.Appointment.System.model.dto;

import com.example.Appointment.System.model.entity.MUser;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserRoleDTO {
    private String role;
    private List<Long> userIds=new ArrayList<>();

}
