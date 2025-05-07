package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class LabTestBookingDTO {
    @Column(nullable = false)
    private String labTestName;
    private String note;
    private String status;
    private Instant  oderDate;
    private Instant deliveryDate;
    private String diagnosticCenterName;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String roadNo;
    private String holdingNo;

}
