package com.sodep.controller;


import com.sodep.Service.TaskService;
import com.sodep.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TasksController {

    @Autowired
    TaskService taskService;

    //Obtiene todas las tareas
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
    public List<Task> getAllTask()  {
    	return taskService.findAllTasks();
    }

    //obtienes detalles de las tareas por su id
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET, produces = "application/json")
	public Task getTaskById(@PathVariable Long id) {
		return taskService.findTaskById(id);
	}

    //agregar tareas
    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST, produces = "application/json")
    public Task addTask(@RequestBody Task taskNew) throws Exception {
        return taskService.saveTask(taskNew);
    }

    //update tarea
    @RequestMapping(value = "/tasks/update", method = RequestMethod.PUT, produces = "application/json")
	public String updateTaskById(@RequestBody Task taskRequest) {
		return taskService.updateTaskById(taskRequest);
	}

    @Transactional
    @RequestMapping(value = "/tasks/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}
