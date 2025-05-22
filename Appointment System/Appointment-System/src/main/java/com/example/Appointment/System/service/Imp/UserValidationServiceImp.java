package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.repository.UserRepo;
import com.example.Appointment.System.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImp implements UserValidationService {
    private final UserRepo userRepo;
    @Override
    public String isExitUserByContact(String contact) {
        if(userRepo.existsByContact(contact)){
            return "This Contact Number is already exit!";
        }
        return "";
    }

    @Override
    public String isEmptyUserContact(String contact) {
        if(contact.isEmpty()){
            return "Mobile Number can't be empty";
        }
        return "";
    }

    @Override
    public String isValidUserContactLength(String contact) {
        if (contact.length() != 11 ){
            return "Mobile Number must be 11 digits";
        }
        return "";
    }

    @Override
    public String isValidUserPasswordLength(String password) {
        if (password.length() < 8 ){
            return "Password must be at least 8 characters long";
        }
        return "";
    }

    @Override
    public String isValidEmailFormat(String email) {
        if(!email.contains("@")){
            return "Email must contain @";
        }
        return "";
    }

    @Override
    public String isValidGender(String gender) {
        Set<String> genders=Set.of("male","female","other");
        if(!genders.contains(gender.toLowerCase())){
            return "Gender must be male,female or other";
        }
        return "";
    }
}
