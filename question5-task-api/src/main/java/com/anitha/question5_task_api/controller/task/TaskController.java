package com.anitha.question5_task_api.controller.task;

import com.anitha.question5_task_api.model.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Database Design", "Design schema", true, "HIGH", "2026-02-10"));
        tasks.add(new Task(2L, "API Implementation", "Code endpoints", false, "MEDIUM", "2026-02-20"));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getByPriority(@PathVariable String priority) {
        return tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markComplete(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.notFound().build();
    }
}