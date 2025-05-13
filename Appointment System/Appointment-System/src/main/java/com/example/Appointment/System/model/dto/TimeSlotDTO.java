package com.example.Appointment.System.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSlotDTO {
    private Long numberOfSlots;
    private Long durable;
    private Long startHour;
}
