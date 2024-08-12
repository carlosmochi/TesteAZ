package com.testeaz.testeaz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.testeaz.testeaz.exceptions.NoTitleException;
import com.testeaz.testeaz.exceptions.InvalidTextSizeException;
import com.testeaz.testeaz.model.TaskModel;
import com.testeaz.testeaz.services.TaskServices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/* Classe de Controle, possui todas os metodos e endpoints que são usados para o frontend acessar o backend.
Normalmente é criada uma outra classe em services para gerenciar regras de negócio, mas todas elas foram
colocadas aqui devido a sua baixa quantidade.*/ 
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {
    final TaskServices taskServices;

    /* Ao inicial o serviço, ele automáticamente conecta esse Controller a seus respectivos Services, permitindo
    usar os metodos da classe Service e portanto conectar ao banco de dados*/
    public TaskController(TaskServices taskServices){
        this.taskServices = taskServices;
    }
// Endpoint POST para criar uma nova entrada no Banco de dados
    // Anotação usada para configurar essa função como um POST na url "/"
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskModel taskData) throws Exception{
        // Caso o título esteja vazio, o sistema lança a exceção NoTitleException ao invéz de qualquer outra ação
        if(!taskData.getTitle().isEmpty()){
            // Caso o Texto ou Descrição violem a limitação de tamanho o sistema lança uma exceção InvalidTextException
            if(this.checkForValidTextSize(taskData.getTitle(),50) && this.checkForValidTextSize(taskData.getDescription(),254)){
                //Caso nenhuma violação das regras de negócio sejam encontradas o sistema segue normalmente
                var createdTask = new TaskModel();

                /* O método copyProperties realiza automaticamente a cópia dos dados recebidos do Frontend, no formato correto, 
                para o objeto que vai ser salvo no Banco de Dados */
                BeanUtils.copyProperties(taskData, createdTask);
                
                /* Força a tarefa que será criada a seguir a regra de negócio: 
                "O campo completed deve ser false por padrão ao criar uma nova tarefa." */
                createdTask.setCompleted(false);
                taskServices.save(createdTask);
                return ResponseEntity.status(HttpStatus.OK).body("Tarefa "+createdTask.getTitle()+" criada com sucesso");
  }
            throw new InvalidTextSizeException("O Titulo ou a Descrição estão muito grandes, por favor ser mais sucinto. Limite de caracteres para Titulo são 50, e para Descrição são 255");
        }
        throw new NoTitleException("Não pode ser criada tarefa sem Titulo");
    }

// Endpoint GET para buscar entradas no Banco de dados
    // Anotação usada para configurar essa função como um GET na url "/"
    @GetMapping
    public List<TaskModel> getAllTasks(){
        return taskServices.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TaskModel> getTaskById(@PathVariable(value = "id") Long id){
        return taskServices.findById(id);
    }

// Endpoint DELETE para remover entradas no Banco de dados
    /* Anotação usada para configurar essa função como um DELETE na url "/{id}" sendo o id uma
    variável de caminho (Path Variable)*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id){
        Optional<TaskModel> taskToBeDeleted = getTaskById(id);
        taskServices.delete(taskToBeDeleted.get());

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa apagada com sucesso");
    }

// Endpoint PUT para alterar uma entrada no Banco de dados
    // Anotação usada para configurar essa função como um PUT na url "/{id}"    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable(value = "id") Long id, @RequestBody TaskModel updatedTask){
        Optional<TaskModel> oldTask = getTaskById(id);
        var newTask = new TaskModel();
        BeanUtils.copyProperties(oldTask, newTask);
        BeanUtils.copyProperties(updatedTask, newTask);
        newTask.setId(id);
        taskServices.save(newTask);

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa alterada com sucesso");
    }
    
// Função usada para checar tamanho de texto    
    public boolean checkForValidTextSize(String text, int size){
        if(text.length() > size)
            return false;
        return true;
    }
    
}
