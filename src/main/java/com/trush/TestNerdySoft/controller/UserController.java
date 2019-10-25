package com.trush.TestNerdySoft.controller;

import com.trush.TestNerdySoft.convertor.UserConvertor;
import com.trush.TestNerdySoft.dto.SignInDTO;
import com.trush.TestNerdySoft.dto.SignUpDTO;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.UserRepository;
import com.trush.TestNerdySoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/account")
        public ResponseEntity<SignUpDTO> createNewAccount(@RequestBody SignUpDTO signUpDTO){
        User account = userService.createUser(userConvertor.fromSignUpDTO(signUpDTO));
        return new ResponseEntity<SignUpDTO>(userConvertor.toSignUpDTO(account), HttpStatus.CREATED);

    }

    @GetMapping("/account/{id}")
        public ResponseEntity<SignUpDTO> getAccount(@PathVariable(name = "id") Long id){
        User account = userService.getUserById(id);
        return new ResponseEntity<SignUpDTO>(userConvertor.toSignUpDTO(account), HttpStatus.OK);
    }

}
