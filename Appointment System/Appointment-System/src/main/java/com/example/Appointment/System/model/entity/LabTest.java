package com.example.Appointment.System.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lab_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String labTestName;
    private String description;
    private String labTestImageUrl;

    @ManyToMany(mappedBy = "labTests",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<DiagnosticCenter> diagnosticCenters=new HashSet<>();
    @OneToMany(mappedBy = "labTest",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<LabTestBooking> labTestBookings=new HashSet<>();
}
