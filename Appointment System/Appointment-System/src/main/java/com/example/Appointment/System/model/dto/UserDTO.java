package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDTO {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contact;
    private String gender;
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String profilePictureUrl;
    private String address;
    private String profession;
    private String designation;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String languagesSpoken;
    private List<String> degrees;
    private Double rating;
    private Boolean availabilityStatus;
    private String firstName;
    private String lastName;
    private MultipartFile profilePicture;
}
