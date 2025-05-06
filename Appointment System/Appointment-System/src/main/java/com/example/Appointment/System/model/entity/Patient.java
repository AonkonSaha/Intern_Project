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
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String patientName;
    @Column(nullable = false)
    private String mobileNumber;
    private String gender;
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    private String role;
    private String profilePictureUrl;
    private String address;
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name="patient_doctor",
               joinColumns = @JoinColumn(name="patient_id"),
               inverseJoinColumns = @JoinColumn(name="doctor_id"))
    private Set<Doctor> doctors=new HashSet<>();

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<LabTestBooking> labTestBookings=new HashSet<>();

    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DoctorBooking>doctorBookings=new HashSet<>();

    public Integer getAge() {
        if (this.dateOfBirth == null) return null;
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
