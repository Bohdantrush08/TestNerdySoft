package com.trush.TestNerdySoft.repository;

import com.trush.TestNerdySoft.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task getOneByName(String name);
    Task getOneById(Long id);

}
