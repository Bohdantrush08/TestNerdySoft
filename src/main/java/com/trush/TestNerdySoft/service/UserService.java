package com.trush.TestNerdySoft.service;

import com.trush.TestNerdySoft.entity.Privilege;
import com.trush.TestNerdySoft.entity.Role;
import com.trush.TestNerdySoft.entity.Task;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.TaskRepository;
import com.trush.TestNerdySoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public User createUser(User user){
        if(isExist(user.getEmail())){
            return getUserByEmail(user.getEmail());
        }
        user = userRepository.save(user);
        return user;

    }

    @Transactional
    public Task setUser(String name, Collection<User> users) {
        Task task = taskRepository.findByName(name);
        if(task != null) {
            task.setUsers((List<User>) users);
            task = taskRepository.save(task);
        }
        else {
            task = new Task();
            task.setUsers((List<User>) users);
            task = taskRepository.save(task);
        }
        return task;
    }

    public void delete(Long id) {
        userRepository.delete(findOne(id));
    }

    public User getUserByEmail(String email){
        return userRepository.getOneByEmail(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUserById(Long id){ return userRepository.getOneById(id); }

    public User findOne(Long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id" +id+" not exist"));
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean isExist(String email){
        return userRepository.getOneByEmail(email) != null;
    }
}

