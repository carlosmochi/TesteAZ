package com.testeaz.testeaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeaz.testeaz.entity.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long>{
    
}
