package com.example.Appointment.System.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDTO {
    private String doctorName;
    private String specialization;
    private String contactNumber;
    private String email;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String address;
    private String languagesSpoken;
    private String education;
    private String gender;
    private Boolean availabilityStatus;
}
