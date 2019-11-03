package com.trush.TestNerdySoft.controller;

import com.trush.TestNerdySoft.convertor.UserConvertor;
import com.trush.TestNerdySoft.dto.SignInDTO;
import com.trush.TestNerdySoft.dto.SignUpDTO;
import com.trush.TestNerdySoft.dto.UsernameDTO;
import com.trush.TestNerdySoft.entity.Task;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.TaskRepository;
import com.trush.TestNerdySoft.repository.UserRepository;
import com.trush.TestNerdySoft.service.TaskService;
import com.trush.TestNerdySoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private TaskService taskService;


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/account")
        public ResponseEntity<SignUpDTO> createNewAccount(@RequestBody SignUpDTO signUpDTO){
        User account = userService.createUser(userConvertor.fromSignUpDTO(signUpDTO));
        return new ResponseEntity<SignUpDTO>(userConvertor.toSignUpDTO(account), HttpStatus.CREATED);

    }

    @GetMapping("/account")
    public List<SignInDTO> findAllUsers(){
        ArrayList<SignInDTO> allUsers = new ArrayList<>();
        for (User user: userService.getAllUsers()){
            allUsers.add(userConvertor.toSigninDTO(user));
        }
        return allUsers;
    }   

    @GetMapping("/account/{id}")
        public ResponseEntity<User> getAccount(@PathVariable(name = "id") Long id){
        User account = userService.getUserById(id);
        return new ResponseEntity<User>(account, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/settasktouser/")
    public ResponseEntity<Task> setTaskToUser(@RequestBody UsernameDTO usernameDTO ){
       // User user = userRepository.findByEmail("string");
        //  userService.setUser("Task1", Arrays.asList(user));
//        Task task = new Task();

        Task task = taskService.getTaskById(usernameDTO.getId());
        task.addUsers(
                userRepository.findByEmail(usernameDTO.getUsername()));
        taskRepository.save(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}