package com.testeaz.testeaz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.testeaz.testeaz.entity.TaskEntity;
import com.testeaz.testeaz.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskServices {
    public final TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskEntity save(TaskEntity taskModel){
        return taskRepository.save(taskModel);
    }

    @Transactional
    public void delete(TaskEntity taskModel){
        taskRepository.delete(taskModel);
    }

    public List<TaskEntity> findAll(){
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> findById(Long id){
        return taskRepository.findById(id);
    }

}
