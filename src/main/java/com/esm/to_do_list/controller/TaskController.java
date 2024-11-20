package com.esm.to_do_list.controller;

import com.esm.to_do_list.dto.TaskRecordDto;
import com.esm.to_do_list.model.TaskModel;
import com.esm.to_do_list.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/tasks")
    public ResponseEntity<TaskModel> saveTask (@RequestBody @Valid TaskRecordDto taskRecordDto){
        var taskModel = new TaskModel();

        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(taskModel));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> getAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id")UUID id){
        Optional<TaskModel> taskOne = taskRepository.findById(id);

        if(taskOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(taskOne.get());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTask (@PathVariable(value = "id") UUID id, @RequestBody @Valid TaskRecordDto taskRecordDto){
        Optional<TaskModel> taskOne = taskRepository.findById(id);

        if(taskOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        var taskModel = taskOne.get();
        BeanUtils.copyProperties(taskRecordDto, taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.save(taskModel));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") UUID id){
        Optional<TaskModel> taskOne = taskRepository.findById(id);

        if(taskOne.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        taskRepository.delete(taskOne.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task delete successfully.");
    }



}
