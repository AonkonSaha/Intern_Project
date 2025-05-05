package com.example.Appointment.System.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lab_test_bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabTestBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String labTestName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAppointment;
    private String note;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime oderDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(name = "diagnostic_center_id")
    private DiagnosticCenter diagnosticCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
