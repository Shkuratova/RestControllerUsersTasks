package com.example.userstasks.controller;
import com.example.userstasks.DTO.TaskDTO;
import com.example.userstasks.models.Task;
import com.example.userstasks.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users/tasks")
public class TaskController {

    public final TaskService service;

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> addTask(@PathVariable(name = "id") Long id, @RequestBody Task task){
        return  ResponseEntity.ok(service.create(task, id));
    }
    @PutMapping(value = "/{id}/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @PathVariable(name = "task_id") Long taskId,
                                        @RequestBody TaskDTO dto){
        return ResponseEntity.ok(service.update(dto, id, taskId));
    }
    @DeleteMapping(value = "/{id}/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name="id") Long id, @PathVariable(name="task_id") Long taskId){
        return ResponseEntity.ok(service.delete(id, taskId));
    }
    @GetMapping
    public ResponseEntity<?> readAll(){
        return ResponseEntity.ok(service.readAll());
    }
    @GetMapping(value = "/{id}/{task_id}")
    public ResponseEntity<?>getTask(@PathVariable(name="id") Long id, @PathVariable(name="task_id") Long taskId) {
        return ResponseEntity.ok(service.read(id, taskId));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?>getTasksByUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.findByUser(id));
    }




}