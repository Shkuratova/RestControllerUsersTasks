package com.example.userstasks.utils;

import com.example.userstasks.DTO.TaskDTO;
import com.example.userstasks.DTO.TaskToListDTO;
import com.example.userstasks.DTO.UserDTO;
import com.example.userstasks.models.Task;
import com.example.userstasks.models.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MappingUtils {
    public TaskDTO MapTask(Task task){
        TaskDTO dto = new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus());
        return dto;
    }
    public UserDTO Map(User user){
        UserDTO dto = new UserDTO();
        dto.setLogin(user.getLogin());
        dto.setTasks(user.getTaskList().stream().map(t-> this.MapTask(t)).collect(Collectors.toList()));
        return dto;
    }
    public TaskToListDTO MapToList(Task task){
        TaskToListDTO dto = new TaskToListDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus());
        dto.setLogin(task.getUser().getLogin());
        dto.setUser_id(task.getUser().getId());
        return dto;
    }

}
