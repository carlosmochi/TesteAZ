package com.testeaz.testeaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeaz.testeaz.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    
}
