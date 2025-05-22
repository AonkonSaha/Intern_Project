package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.entity.LabTest;
import com.example.Appointment.System.repository.LabTestRepo;
import com.example.Appointment.System.service.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LabTestServiceImp implements LabTestService {
    private final LabTestRepo labTestRepo;

    @Override
    public LabTest saveLabTest(LabTest labTest) {
        labTestRepo.save(labTest);
        return labTest;
    }
    @Override
    public boolean isExitLabTestById(Long id) {
        return labTestRepo.existsById(id);
    }

    @Override
    public LabTest getLabTestById(Long id) {
        Optional<LabTest> labTest=labTestRepo.findById(id);
        if(labTest.isEmpty()){
            return null;
        }
        return labTest.get();
    }

    @Override
    public void removeLabTestById(Long id) {
        if(!isExitLabTestById(id)){
            return;
        }
        labTestRepo.deleteById(id);
    }

    @Override
    public LabTest updateLabTest(Long id, LabTestDTO labTestDTO) {
        Optional<LabTest> labTest=labTestRepo.findById(id);
        if(labTest.isEmpty()){
            return null;
        }
        labTest.get().setLabTestName(labTestDTO.getLabTestName());
        labTest.get().setLabTestImageUrl(labTestDTO.getLabTestImageUrl());
        labTestRepo.save(labTest.get());
        return labTest.get();
    }

    @Override
    public List<LabTest> getAllLabTest() {

        return labTestRepo.findAll();
    }

    @Override
    public boolean isExitLabTestName( String labTestName) {
        return labTestRepo.existsByLabTestName(labTestName);
    }
}
