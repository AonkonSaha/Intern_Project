package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.entity.LabTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabTestMapper {
    public LabTest toLabTest(LabTestDTO labTestDTO) {
        return LabTest.builder()
                .LabTestName(labTestDTO.getLabTestName())
                .labTestImageUrl(labTestDTO.getLabTestImageUrl())
                .build();
    }

    public LabTestDTO toLabTestDTO(LabTest labTest) {
        return LabTestDTO.builder()
                .LabTestName(labTest.getLabTestName())
                .labTestImageUrl(labTest.getLabTestImageUrl())
                .build();
    }
}
