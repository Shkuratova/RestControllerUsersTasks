package com.example.userstasks.service;

import com.example.userstasks.DTO.UserDTO;
import com.example.userstasks.exeption.AlreadyExistsException;
import com.example.userstasks.exeption.DoesntExistsException;
import com.example.userstasks.exeption.EmptyFieldException;
import com.example.userstasks.models.User;
import com.example.userstasks.repository.UserRepository;
import com.example.userstasks.utils.MappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository rep;
    private final MappingUtils mapper;
    public User registration(User user){
        if(user.getPassword().isEmpty())
            throw new EmptyFieldException("Поле password не может быть пустым");
        if(user.getLogin().isEmpty())
            throw new EmptyFieldException("Поле login не может быть пустым");
        Optional<User> u = rep.findByLogin(user.getLogin());
        if(u.isPresent())
            throw new AlreadyExistsException("Логин " + user.getLogin() + " уже занят");
        return rep.save(user);
    }
    public UserDTO getUser(Long id){
        Optional<User> u = rep.findById(id);
        if(u.isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        return mapper.Map(u.get());
    }
    public UserDTO updateUser(Long id, User user) {
        Optional<User> u = rep.findById(id);
        if (u.isEmpty())
            throw new DoesntExistsException("Пользователя с id=" + id + " не существует");
        if ((user.getLogin() == null || user.getLogin().isEmpty()) && (user.getPassword() == null || user.getPassword().isEmpty()))
            throw new EmptyFieldException("Хотя бы одно поле login или password должны быть заполнены");
        if (rep.findByLogin(user.getLogin()).isPresent())
            throw new AlreadyExistsException("Логин " + user.getLogin() + " уже занят");
        if (user.getLogin() != null || !user.getLogin().isEmpty())
                u.get().setLogin(user.getLogin());
        if (user.getPassword() != null || !user.getPassword().isEmpty())
                u.get().setPassword(user.getPassword());
        return mapper.Map(rep.save(u.get()));
    }
    public UserDTO deleteUser(Long id){
        Optional<User> u = rep.findById(id);
        if (u.isEmpty())
            throw new DoesntExistsException("Пользователя с id=" + id + " не существует");
         rep.delete(u.get());
         return mapper.Map(u.get());
    }
    public List<UserDTO> readAll(){
        List<User> u = rep.findAll();
        if(u.isEmpty())
            throw new DoesntExistsException("Список пользователей пуст");
        List<UserDTO> d = u.stream().map(t-> mapper.Map(t)).collect(Collectors.toList());
        return d;
    }
}
