package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String doctorName;
    @NotNull
    private String designation;
    @NotNull
    private String contactNumber;
    @NotNull
    private String email;
    @NotNull
    private String licenseNumber;
    @NotNull
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String address;
    private String languagesSpoken;
    @NotNull
    private List<String> degrees=new ArrayList<>();
    @NotNull
    private String gender;
    private Boolean availabilityStatus;
    private String profilePictureUrl;
    private Double rating;
    @NotNull
    private String password;
    private String degreesString;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}
