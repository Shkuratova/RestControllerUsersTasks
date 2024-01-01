package com.example.userstasks.DTO;

import lombok.Data;

@Data
public class TaskToListDTO {
    private Long id;
    private String title;
    private Boolean status;
    private String login;
    private Long user_id;
}
