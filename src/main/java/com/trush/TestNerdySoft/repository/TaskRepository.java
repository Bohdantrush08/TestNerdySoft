package com.trush.TestNerdySoft.repository;

import com.trush.TestNerdySoft.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getOneByName(String name);
    Task getOneById(Long id);
    ArrayList<Task> getAllByUserId(Long id);
    ArrayList<Task> getAllByUserEmail(String email);
    Task findByName(String name);
}
