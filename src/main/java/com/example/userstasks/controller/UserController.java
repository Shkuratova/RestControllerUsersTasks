package com.example.userstasks.controller;

import com.example.userstasks.models.User;
import com.example.userstasks.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {

        return ResponseEntity.ok(service.registration(user));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?>getUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.getUser(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?>updateUser(@PathVariable(name = "id") Long id, @RequestBody User dto){
        return ResponseEntity.ok(service.updateUser(id, dto));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.readAll());
    }
}
