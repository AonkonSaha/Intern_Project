package com.example.Appointment.System.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabTestDTO {
    private Long id;
    private String labTestName;
    private String description;
    private String labTestImageUrl;
}
