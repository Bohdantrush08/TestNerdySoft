package com.trush.TestNerdySoft.controller;

import com.trush.TestNerdySoft.convertor.TaskConvertor;
import com.trush.TestNerdySoft.dto.TaskDTO;
import com.trush.TestNerdySoft.dto.TaskGetDTO;
import com.trush.TestNerdySoft.entity.Task;
import com.trush.TestNerdySoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskConvertor taskConvertor;

    @PostMapping("/task")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TaskDTO> createNewTask(@RequestBody TaskDTO taskDTO){
        Task task = taskConvertor.fromTaskDTO(taskDTO);
        task = taskService.createTask(task);
        return new ResponseEntity<>(taskConvertor.toTaskDTO(task), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskGetDTO> getTask(@PathVariable(name = "id") Long id){
       Task task = taskService.getTaskById(id);
       return new ResponseEntity<>(taskConvertor.toTaskGetDTO(task),HttpStatus.OK);
    }

    @GetMapping("/task")
    public List<TaskGetDTO> fidAll(){
        ArrayList<TaskGetDTO> allTasksDTO = new ArrayList<>();
        for (Task task: taskService.getAllTasks()){
            allTasksDTO.add(taskConvertor.toTaskGetDTO(task));
        }
        return allTasksDTO;
    }


    @DeleteMapping("/task/{id}")
    public  void delete(@PathVariable(name = "id")Long id){
        taskService.delete(id);
    }
}
