package com.testeaz.testeaz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.testeaz.testeaz.exceptions.TituloVazioException;
import com.testeaz.testeaz.entity.TaskModel;
import com.testeaz.testeaz.exceptions.TamanhoDeTextoInvalidoException;
import com.testeaz.testeaz.services.TaskServices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {
    final TaskServices taskServices;

    public TaskController(TaskServices taskServices){
        this.taskServices = taskServices;
    }

    @PostMapping("/tasks")
    public ResponseEntity<String> createTask(@RequestBody TaskModel taskData) throws Exception{
        if(!taskData.getTitle().isEmpty()){
            if(this.checarTamanhoValidoDeTexto(taskData.getTitle(),50) && this.checarTamanhoValidoDeTexto(taskData.getDescription(),254)){
                var createdTask = new TaskModel();

                BeanUtils.copyProperties(taskData, createdTask);               
                /* Força a tarefa que será criada a seguir a regra de negócio: 
                "O campo completed deve ser false por padrão ao criar uma nova tarefa." */
                createdTask.setCompleted(false);
                taskServices.save(createdTask);
                return ResponseEntity.status(HttpStatus.OK).body("Tarefa "+createdTask.getTitle()+" criada com sucesso");
  }
            throw new TamanhoDeTextoInvalidoException("O Titulo ou a Descrição estão muito grandes, por favor ser mais sucinto. Limite de caracteres para Titulo são 50, e para Descrição são 255");
        }
        throw new TituloVazioException("Não pode ser criada tarefa sem Titulo");
    }

    @GetMapping("/tasks")
    public List<TaskModel> getAllTasks(){
        return taskServices.findAll();
    }

    @GetMapping("tasks/{id}")
    public Optional<TaskModel> getTaskById(@PathVariable(value = "id") Long id){
        return taskServices.findById(id);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id){
        Optional<TaskModel> taskToBeDeleted = getTaskById(id);
        taskServices.delete(taskToBeDeleted.get());

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa apagada com sucesso");
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<String> updateTask(@PathVariable(value = "id") Long id, @RequestBody TaskModel updatedTask){
        Optional<TaskModel> oldTask = getTaskById(id);
        var newTask = new TaskModel();
        BeanUtils.copyProperties(oldTask, newTask);
        BeanUtils.copyProperties(updatedTask, newTask);
        newTask.setId(id);
        taskServices.save(newTask);

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa alterada com sucesso");
    }
    
    public boolean checarTamanhoValidoDeTexto(String text, int size){
        if(text.length() > size)
            return false;
        return true;
    }
    
}
