package com.testeaz.testeaz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.testeaz.testeaz.entity.TaskModel;
import com.testeaz.testeaz.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskServices {
    public final TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel save(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }

    @Transactional
    public void delete(TaskModel taskModel){
        taskRepository.delete(taskModel);
    }

    public List<TaskModel> findAll(){
        return taskRepository.findAll();
    }

    public Optional<TaskModel> findById(Long id){
        return taskRepository.findById(id);
    }

}
