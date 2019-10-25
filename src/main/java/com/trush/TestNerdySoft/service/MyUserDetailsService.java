package com.trush.TestNerdySoft.service;


import com.trush.TestNerdySoft.entity.Privilege;
import com.trush.TestNerdySoft.entity.Role;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.RoleRepository;
import com.trush.TestNerdySoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Transactional
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.getOneByEmail(email);

        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(
                            roleRepository.findByName("ROLE_USER"))));

        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> getAuthorities(List<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(List<Role> roles){
        List<Privilege> collection = new ArrayList<>();
        List<String> privileges = new ArrayList<>();

        for(Role role:roles){
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }

        for(Privilege item:collection){
            privileges.add(item.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(String privilege:privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }

        return authorities;
    }
}



