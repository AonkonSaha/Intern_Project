package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.entity.LabTest;

import java.util.List;

public interface LabTestService {


    LabTest saveLabTest(LabTest labTest);
    boolean isExitLabTestById(Long id);
    LabTest getLabTestById(Long id);
    void removeLabTestById(Long id);
    LabTest updateLabTest(Long id, LabTestDTO labTestDTO);
    List<LabTest> getAllLabTest();
    boolean isExitLabTestName( String labTestName);
}
