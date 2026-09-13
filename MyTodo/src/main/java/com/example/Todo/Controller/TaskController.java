package com.example.Todo.Controller;

import com.example.Todo.Entity.Task;
import com.example.Todo.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String findAll(Model model) //thymeleaf template model
    {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks); // created tasks attribute which we can use in our html view/file
        return "tasks"; //'tasks' = view name ,then thymeleaf renders the html and req is sent to browser
    }

    @PostMapping
    public String createTask(@RequestParam String title) // 'title' matches with name attribute in form elem
    {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);

        taskService.saveTask(task);
        return "redirect:/tasks"; //we are redirecting to this controller '/tasks, GET' only then everything inside html builds up again
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id) {

        taskService.toggleTask(id);
        return "redirect:/tasks";
    }

//    @GetMapping("/edit/{id}")
//    public String editTask(@PathVariable Long id, Model model)
//    {
//        Task Task = TaskService.getTaskById(id);
//        model.addAttribute("Task",Task);
//        return "edit-Task";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editTask(@PathVariable Long id, Model model)
//    {
//
//    }
}

