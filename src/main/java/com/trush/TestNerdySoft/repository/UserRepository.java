package com.trush.TestNerdySoft.repository;

import com.trush.TestNerdySoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
     User getOneByEmail(String email);
     User getOneById(Long id);
     User findByEmail(String email);

}
