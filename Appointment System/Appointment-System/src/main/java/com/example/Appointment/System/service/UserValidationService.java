package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.UserDTO;

public interface UserValidationService {

    String isExitUserByContact(String contact);
    String isEmptyUserContact(String contact);
    String isValidUserContactLength(String contact);
    String isValidUserPasswordLength(String password);
    String isValidEmailFormat(String email);
    String isValidGender(String gender);

}
