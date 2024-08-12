package com.testeaz.testeaz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.testeaz.testeaz.model.TaskModel;
import com.testeaz.testeaz.repository.TaskRepository;

import jakarta.transaction.Transactional;

/* Serviço Spring responsavel por conectart as lógicas de negócio do controller com o repositório que 
se comunica com o banco de dados.*/

@Service
public class TaskServices {
    public final TaskRepository taskRepository;
    public TaskServices(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    // Função básica de inserção no banco de dados
    @Transactional
    public TaskModel save(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }

    // Função básica de deletar no banco de dados
    @Transactional
    public void delete(TaskModel taskModel){
        taskRepository.delete(taskModel);
    }

    // Busca todas as Tasks dentro do banco de dados
    public List<TaskModel> findAll(){
        return taskRepository.findAll();
    }

    // Busca uma entrada específica no banco de dados de acordo com o Id
    public Optional<TaskModel> findById(Long id){
        return taskRepository.findById(id);
    }

}
