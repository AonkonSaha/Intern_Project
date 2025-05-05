package com.example.Appointment.System.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private String specialization;
    private String contactNumber;
    private String email;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String address;
    private String profilePictureUrl;
    private String languagesSpoken;
    private String education;
    private Double rating;
    private String gender;
    private Boolean availabilityStatus;
    private String role;
    @ManyToMany(mappedBy = "doctors",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Patient> patients=new HashSet<>();
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DoctorBooking> doctorBookings=new HashSet<>();


}
