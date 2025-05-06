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

import java.util.List;
import java.util.Optional;

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
                .doctor(doctorBooking.getDoctor())
                .patient(doctorBooking.getPatient())
                .status(doctorBooking.getStatus())
                .build();
    }
    public DoctorBooking toDoctorBooking(DoctorBookingDTO doctorBookingDTO) {
        String patientName= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Patient> patient=patientRepo.findByPatientName(patientName);
        Optional<Doctor> doctor=doctorRepo.findByLicenseNumber(doctorBookingDTO.getLicenseNumber());

        DoctorBooking doctorBooking= DoctorBooking.builder()
                .bookingDate(doctorBookingDTO.getBookingDate())
                .appointmentDate(doctorBookingDTO.getAppointmentDate())
                .note(doctorBookingDTO.getNote())
                .status(Status.Confirmed.name())
                .build();
        patient.get().getDoctorBookings().add(doctorBooking);
        doctor.get().getDoctorBookings().add(doctorBooking);
        patient.get().getDoctors().add(doctor.get());
        doctor.get().getPatients().add(patient.get());
        doctorBooking.setPatient(patient.get());
        doctorBooking.setDoctor(doctor.get());
        patientRepo.save(patient.get());
        doctorRepo.save(doctor.get());
        return doctorBooking;

    }


    public List<DoctorBookingDTO> toDoctorBookingDTOS(List<DoctorBooking> doctorBookings) {
        List<DoctorBookingDTO>doctorBookingDTOS=new java.util.ArrayList<>();
        for (DoctorBooking doctorBooking:doctorBookings){
            doctorBookingDTOS.add(toDoctorBookingDTO(doctorBooking));
        }
        return doctorBookingDTOS;
    }
}
