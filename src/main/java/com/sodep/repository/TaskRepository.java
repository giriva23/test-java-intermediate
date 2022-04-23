package com.sodep.repository;

import com.sodep.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findById(Long id);


    void deleteById(Long id);
}
