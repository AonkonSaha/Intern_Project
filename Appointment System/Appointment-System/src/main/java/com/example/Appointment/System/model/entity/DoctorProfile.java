package com.example.Appointment.System.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private String designation;
    private String licenseNumber;
    private Integer yearsOfExperience;
    private String hospitalOrClinicName;
    private String profilePictureUrl;
    private String languagesSpoken;
    private List<String> degrees=new ArrayList<>();
    private Double rating;
    private Boolean availabilityStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MUser user;

    @ManyToMany(mappedBy = "doctorProfiles",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PatientProfile> patientProfiles =new HashSet<>();

    @OneToMany(mappedBy = "doctorProfile",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DoctorBooking> doctorBookings=new HashSet<>();


}
