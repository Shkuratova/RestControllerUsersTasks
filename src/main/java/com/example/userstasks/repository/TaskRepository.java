package com.example.userstasks.repository;

import com.example.userstasks.models.Task;
import com.example.userstasks.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAll();
    List<Task> findAllByUser(User user);

}
