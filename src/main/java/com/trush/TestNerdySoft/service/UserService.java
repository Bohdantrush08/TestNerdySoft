package com.trush.TestNerdySoft.service;

import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        if(isExist(user.getEmail())){
            return getUserByEmail(user.getEmail());
        }
        user = userRepository.save(user);
        return user;

    }

    public void delete(Long id) {
        userRepository.delete(findOne(id));
    }

    public User getUserByEmail(String email){
        return userRepository.getOneByEmail(email);
    }
    public User getUserById(Long id){ return userRepository.getOneById(id); }

    public User findOne(Long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User with id" +id+" not exist"));
    }

    public boolean isExist(String email){
        return userRepository.getOneByEmail(email) != null;
    }
}

