package com.sodep.Service;

import com.sodep.entities.Task;
import com.sodep.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private static int MAX_TASK = 5;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findListTasksById(Long id) {
        List<Task> taskList = taskRepository.findAll();
        List<Task> taskListId = new ArrayList<>();
        taskList.stream().forEach((task -> {
            if (task.getAssignee().getId().equals(id))
                taskListId.add(task);
        }));
        return taskListId;
    }

    @Override
    public Task findTaskById(Long id) {
        Task task = taskRepository.findById(id);
        return task;
    }

    @Override
    public Task saveTask(Task taskNew) throws Exception{
        if (taskNew != null) {
            //validar cant de tareas asignadas
            boolean fatigado = findListTasksById(taskNew.getAssignee().getId()).size() >= MAX_TASK;
            if (!fatigado) {
                return taskRepository.save(taskNew);
            }else{
                throw new Exception("La persona ya tiene asignada un máximo de 5 tareas");
            }
        }
        return new Task();
    }

    @Override
    public String deleteTask(Long id) {
        if (isPresentTask(id)) {
            taskRepository.deleteById(id);
            return "Tarea eliminada correctamente.";
        }
        return "La tarea no pudo ser eliminada";
    }

    @Override
    public String updateTaskById(Task taskUpdated) {
        Long num = taskUpdated.getId();
        if (isPresentTask(num)) {
            Task taskToUpdate = new Task();
            taskToUpdate.setId(taskUpdated.getId());
            taskToUpdate.setAssignee(taskUpdated.getAssignee());
            taskToUpdate.setCompletedAt(taskUpdated.getCompletedAt());
            taskToUpdate.setDescription(taskUpdated.getDescription());
            taskToUpdate.setCreatedAt(taskUpdated.getCreatedAt());
            taskToUpdate.setDue(taskUpdated.getDue());
            //validar cant de tareas asignadas
            boolean fatigado = findListTasksById(taskToUpdate.getAssignee().getId()).size() >= MAX_TASK;
            if (!fatigado) {
                taskRepository.save(taskToUpdate);
                return "La tarea ha sido actualizada";
            }else{
                return "La tarea no pudo ser actualizada, la persona asignada ya alcanzo el máximo de tareas";
            }

        }
        return "Error al actualizar, la tarea no existe";
    }

    private boolean isPresentTask(Long id){
        Task task = findTaskById(id);
        return task != null;
    }

}
