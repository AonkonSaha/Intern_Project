package com.example.Appointment.System.model.dto;

import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class DoctorBookingDTO {
    private Long doctorId;
    private Long patientId;
    private Long doctorBookingId;
//    @JsonProperty(value = "doctor_name",required = true,defaultValue = "doctor_name")
    private String doctorName;
    private String licenseNumber;
    private String hospitalOrClinicName;
    private List<String> degrees=new ArrayList<>();
    private String designation;
    private String note;
    private String status;
    private LocalDate bookingDate;
    private LocalDate appointmentDate;
    private String slotTime;
    private String doctorImageUrl;
    private String gender;
    private String email;
    private String contact;



}
