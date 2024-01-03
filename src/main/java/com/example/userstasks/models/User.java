package com.example.userstasks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Task> taskList;
}
