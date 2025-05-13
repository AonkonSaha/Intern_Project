package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class DoctorDTO {
    private Long id;
    private String doctorName;
    private String designation;
    private String contactNumber;
    private String email;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String address;
    private String languagesSpoken;
    private List<String> degrees=new ArrayList<>();
    private String gender;
    private Boolean availabilityStatus;
    private String profilePictureUrl;
    private Double rating;
    private String password;
    private String degreesString;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}
