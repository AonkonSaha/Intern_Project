package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.PasswordDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    MUser saveUser(MUser user);
    MUser getUserByName(String username) ;
    boolean isExitUserById(Long id);
    MUser updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    MUser getUserById(Long id);
    boolean isExitUserByContact(String contact);
    public MUser findUserByContact(String contact);
    MUser updatePatientWithOutPassword(String fullName, String email, String dob, String gender, MultipartFile photo);
    void  updateUserPassword(String contact, PasswordDTO passwordDTO);
    boolean isExitUserPassword(String password);
    String validateUserDetails(UserDTO userDTO);
}
