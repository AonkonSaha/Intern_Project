package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.enums.Status;
import com.example.Appointment.System.exception.LabTestBookingNotFoundException;
import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.LabTestBookingDTO;
import com.example.Appointment.System.model.entity.DiagnosticCenter;
import com.example.Appointment.System.model.entity.LabTestBooking;
import com.example.Appointment.System.model.entity.Patient;
import com.example.Appointment.System.repository.DiagnosticCenterRepo;
import com.example.Appointment.System.repository.LabTestBookingRepo;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LabTestBookingMapper {
    private final DiagnosticCenterRepo diagnosticCenterRepo;
    private final PatientRepo patientRepo;
    private final LabTestBookingRepo labTestBookingRepo;
    public LabTestBooking toLabTestBooking(LabTestBookingDTO labTestBookingDTO) throws LabTestBookingNotFoundException, PatientNotFoundException {
//        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<Patient>patient=patientRepo.findPatient(customUserDetails.getUsername(),customUserDetails.getEmail(),customUserDetails.getMobileNumber());
          Optional<Patient> patient=patientRepo.findByPatientName(SecurityContextHolder.getContext().getAuthentication().getName());
                Optional<DiagnosticCenter> diagnosticCenter=diagnosticCenterRepo.findDiagnosticCenter(
                labTestBookingDTO.getLabTestName(),
                labTestBookingDTO.getCountry(),
                labTestBookingDTO.getCity(),
                labTestBookingDTO.getAddress(),
                labTestBookingDTO.getRoadNo(),
                labTestBookingDTO.getHoldingNo()
        );
        if(diagnosticCenter.isEmpty()){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        if(patient.isEmpty()){
            throw new PatientNotFoundException("Patient doesn't exit");
        }

        LabTestBooking labTestBooking= LabTestBooking.builder()
                .labTestName(labTestBookingDTO.getLabTestName())
                .oderDate(labTestBookingDTO.getOderDate())
                .status(Status.Confirmed.name())
                .deliveryDate(labTestBookingDTO.getDeliveryDate())
                .note(labTestBookingDTO.getNote())
                .build();
       patient.get().getLabTestBookings().add(labTestBooking);
       diagnosticCenter.get().getLabTestBookings().add(labTestBooking);
       labTestBooking.setPatient(patient.get());
       labTestBooking.setDiagnosticCenter(diagnosticCenter.get());
       patientRepo.save(patient.get());
       diagnosticCenterRepo.save(diagnosticCenter.get());
       labTestBookingRepo.save(labTestBooking);
       return labTestBooking;
    }

    public LabTestBookingDTO toLabTestBookingDTO(LabTestBooking labTestBooking) {
        return LabTestBookingDTO.builder()
                .labTestName(labTestBooking.getLabTestName())
                .oderDate(labTestBooking.getOderDate())
                .deliveryDate(labTestBooking.getDeliveryDate())
                .note(labTestBooking.getNote())
                .diagnosticCenterName(labTestBooking.getDiagnosticCenter().getDiagnosticCenterName())
                .status(labTestBooking.getStatus())
                .build();
    }

    public List<LabTestBookingDTO> toLabTestBookingDTOS(List<LabTestBooking> allLabTestBooking) {
        List<LabTestBookingDTO>labTestBookingDTOS=new ArrayList<>();
        for (LabTestBooking labTestBooking:allLabTestBooking){
            labTestBookingDTOS.add(toLabTestBookingDTO(labTestBooking));
        }
        return labTestBookingDTOS;
    }
}
