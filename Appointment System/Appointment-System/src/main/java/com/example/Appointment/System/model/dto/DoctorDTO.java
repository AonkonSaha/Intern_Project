package com.example.Appointment.System.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorDTO {
    private String doctorName;
    private String designation;
    private String contactNumber;
    private String email;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String address;
    private String languagesSpoken;
    private List<String> degrees;
    private String gender;
    private Boolean availabilityStatus;
    private String profilePictureUrl;
    private Double rating;
    private String password;

}
