package com.trush.TestNerdySoft.repository;

import com.trush.TestNerdySoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
     User getOneByEmail(String email);
     User getOneById(Long id);
}
