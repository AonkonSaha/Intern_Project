package com.example.Appointment.System.model.dto;

import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DoctorBookingDTO {
    @JsonProperty(value = "doctor_name",required = true,defaultValue = "doctor_name")
    private String doctorName;
    private String licenseNumber;
    private String hospitalOrClinicName;
    private List<String> degrees;
    private String designation;
    private String note;
    private String status;
    private Instant  bookingDate;
    private Instant appointmentDate;

}
