package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.enums.Status;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.Doctor;
import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.model.entity.Patient;
import com.example.Appointment.System.repository.DoctorRepo;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorBookingMapper {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    public DoctorBookingDTO toDoctorBookingDTO(DoctorBooking doctorBooking) {

        return DoctorBookingDTO.builder()
                .bookingDate(doctorBooking.getBookingDate())
                .appointmentDate(doctorBooking.getAppointmentDate())
                .note(doctorBooking.getNote())
                .status(doctorBooking.getStatus())
                .doctorName(doctorBooking.getDoctor().getDoctorName())
                .designation(doctorBooking.getDoctor().getDesignation())
                .degrees(doctorBooking.getDoctor().getDegrees() )
                .hospitalOrClinicName(doctorBooking.getDoctor().getHospitalOrClinicName())
                .licenseNumber(doctorBooking.getDoctor().getLicenseNumber())
                .status( doctorBooking.getStatus())
                .build();
    }
    public DoctorBooking toDoctorBooking(DoctorBookingDTO doctorBookingDTO) {
        String patientName= SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient=patientRepo.findByPatientName(patientName);
        Doctor doctor=doctorRepo.findByLicenseNumber(doctorBookingDTO.getLicenseNumber());

        DoctorBooking doctorBooking= DoctorBooking.builder()
                .bookingDate(doctorBookingDTO.getBookingDate())
                .appointmentDate(doctorBookingDTO.getAppointmentDate())
                .note(doctorBookingDTO.getNote())
                .status(Status.Confirmed.name())
                .build();
        patient.getDoctorBookings().add(doctorBooking);
        doctor.getDoctorBookings().add(doctorBooking);
        patient.getDoctors().add(doctor);
        doctor.getPatients().add(patient);
        doctorBooking.setPatient(patient);
        doctorBooking.setDoctor(doctor);
        patientRepo.save(patient);
        doctorRepo.save(doctor);
        return doctorBooking;

    }


}
