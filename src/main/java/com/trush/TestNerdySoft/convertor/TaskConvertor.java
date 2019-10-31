package com.trush.TestNerdySoft.convertor;

import com.trush.TestNerdySoft.dto.TaskDTO;
import com.trush.TestNerdySoft.dto.TaskGetDTO;
import com.trush.TestNerdySoft.entity.Task;
import com.trush.TestNerdySoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskConvertor {

    @Autowired
    private UserService userService;

    public TaskDTO toTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setText(task.getText());
        taskDTO.setUserId(task.getUser().getId());
        return taskDTO;
    }

    public Task fromTaskDTO(TaskDTO taskDTO){
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setText(taskDTO.getText());
        task.setUser(userService.getUserById(taskDTO.getUserId()));
        return task;
    }

    public TaskGetDTO toTaskGetDTO(Task task){
     TaskGetDTO taskGetDTO = new TaskGetDTO();
     taskGetDTO.setId(task.getId());
     taskGetDTO.setName(task.getName());
     taskGetDTO.setText(task.getText());

     return taskGetDTO;
    }

    public Task fromTaskGetDTO(TaskGetDTO taskGetDTO){
        Task task = new Task();
        task.setId(taskGetDTO.getId());
        task.setName(taskGetDTO.getName());
        task.setText(taskGetDTO.getText());
        return task;
    }


}
