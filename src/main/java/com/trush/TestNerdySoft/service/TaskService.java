package com.trush.TestNerdySoft.service;

import com.trush.TestNerdySoft.entity.Task;
import com.trush.TestNerdySoft.entity.User;
import com.trush.TestNerdySoft.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        task = taskRepository.save(task);
        return task;
    }

    public Task updateTask(Task task){

        task =taskRepository.save(task);
        return task;
    }


    public void delete(Long id){taskRepository.delete(findOne((id)));}

    public Task findOne(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Task with id " +id+" not exist"));
    }
    public Task getTaskById(Long id){
        return taskRepository.getOneById(id);
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public ArrayList<Task> getAllTaskByUser(String username){
        return taskRepository.getAllByUserEmail(username);
    }
}
