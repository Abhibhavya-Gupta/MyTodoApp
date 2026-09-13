package com.example.Todo.Service;

import com.example.Todo.Entity.Task;
import com.example.Todo.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Long id){
        //we could use Optional<Task> here
        return taskRepository.findById(id).orElse(null);
    }

    public void saveTask(Task task)
    {
        taskRepository.save(task);
    }

    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id)
    {
        taskRepository.deleteById(id);
    }


    public void toggleTask(Long id) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException("invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
