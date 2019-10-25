package com.trush.TestNerdySoft.convertor;

import com.trush.TestNerdySoft.dto.SignUpDTO;
import com.trush.TestNerdySoft.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserConvertor {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public SignUpDTO toSignUpDTO(User user) {
        SignUpDTO signUpDTO = new SignUpDTO();

        signUpDTO.setName(user.getName());
        signUpDTO.setSurname(user.getSurname());
        signUpDTO.setEmail(user.getEmail());
        signUpDTO.setPassword(user.getPassword());

        return signUpDTO;
    }

    public User fromSignUpDTO(SignUpDTO signUpDTO) {
        User user = new User();

        user.setName(signUpDTO.getName());
        user.setSurname(signUpDTO.getSurname());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setEnabled(true);


        return user;
    }
}