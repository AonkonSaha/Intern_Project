package com.example.Appointment.System.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientProfile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    private String profilePictureUrl;
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MUser user;

    @ManyToMany
    @JoinTable(name="patient_doctor",
               joinColumns = @JoinColumn(name="patient_id"),
               inverseJoinColumns = @JoinColumn(name="doctor_id"))
    private Set<DoctorProfile> doctorProfiles =new HashSet<>();

    @OneToMany(mappedBy = "patientProfile",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<LabTestBooking> labTestBookings=new HashSet<>();

    @OneToMany(mappedBy = "patientProfile",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DoctorBooking>doctorBookings=new HashSet<>();

    public Integer getAge() {
        if (this.dateOfBirth == null) return null;
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
