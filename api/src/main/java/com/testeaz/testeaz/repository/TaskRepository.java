package com.testeaz.testeaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.testeaz.testeaz.model.TaskModel;

/* Classe Repositório realiza automaticamente a conexão com o banco de dados informado em
application.properties. o módulo JpaRepository injeta as funções básicas de conexão na classe,
permitindo o sistema realizar queries automáticamente através de funções herdadas.*/
public interface TaskRepository extends JpaRepository<TaskModel, Long>{
    
}
