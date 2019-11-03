package com.trush.TestNerdySoft.convertor;

import com.trush.TestNerdySoft.dto.SignInDTO;
import com.trush.TestNerdySoft.dto.SignUpDTO;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.RoleRepository;
import com.trush.TestNerdySoft.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserConvertor {

    @Autowired
    private RoleRepository roleRepository;


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
        user.setRoles(Arrays.asList(
                roleRepository.findByName("ROLE_USER")));



        return user;
    }

    public SignInDTO toSigninDTO(User user){
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setIdUser(user.getId());
        signInDTO.setEmail(user.getEmail());
        return  signInDTO;
    }

    public User fromSigninDTO(SignInDTO signInDTO){
        User user = new User();
        user.setId(signInDTO.getIdUser());
        user.setEmail(signInDTO.getEmail());
        return user;
    }


}