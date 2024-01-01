package com.example.userstasks.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String login;
    private List<TaskDTO> tasks;
}
