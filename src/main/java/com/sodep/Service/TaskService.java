package com.sodep.Service;

import com.sodep.entities.Task;

import java.util.List;

public interface TaskService {
    public List<Task> findAllTasks();

   public List<Task> findListTasksById(Long id);

   public Task findTaskById(Long id);

    public Task saveTask(Task taskNew) throws Exception;

    public String deleteTask(Long id);

    public String updateTaskById(Task taskUpdate);
}
