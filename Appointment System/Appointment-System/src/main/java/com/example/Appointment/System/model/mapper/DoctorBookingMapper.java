package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.enums.Status;
import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.repository.DoctorRepo;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
                .designation(doctorBooking.getDoctorProfile().getDesignation())
                .degrees(doctorBooking.getDoctorProfile().getDegrees() )
                .hospitalOrClinicName(doctorBooking.getDoctorProfile().getHospitalOrClinicName())
                .licenseNumber(doctorBooking.getDoctorProfile().getLicenseNumber())
                .status(doctorBooking.getStatus())
                .doctorName(doctorBooking.getDoctorProfile().getDoctorName())
                .build();
    }
    public DoctorBooking toDoctorBooking(DoctorBookingDTO doctorBookingDTO)  {
        String patientName= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PatientProfile> patient=patientRepo.findByPatientName(patientName);
        Optional<DoctorProfile> doctor=doctorRepo.findByLicenseNumber(doctorBookingDTO.getLicenseNumber());
        if(patient.isEmpty()){
            throw new IllegalArgumentException("Patient doesn't found");
        }
        if(doctor.isEmpty()){
            throw new IllegalArgumentException("Doctor doesn't exit");
        }

        DoctorBooking doctorBooking= DoctorBooking.builder()
                .bookingDate(doctorBookingDTO.getBookingDate())
                .appointmentDate(doctorBookingDTO.getAppointmentDate())
                .note(doctorBookingDTO.getNote())
                .status(Status.Confirmed.name())
                .build();
        patient.get().setDoctorBookings(Set.of(doctorBooking));
        doctor.get().setDoctorBookings(Set.of(doctorBooking));
        patient.get().setDoctorProfiles(Set.of(doctor.get()) );
        doctor.get().setPatientProfiles(Set.of(patient.get()) );
        doctorBooking.setPatientProfile(patient.get());
        doctorBooking.setDoctorProfile(doctor.get());
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
