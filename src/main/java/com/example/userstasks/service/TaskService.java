package com.example.userstasks.service;

import com.example.userstasks.DTO.TaskDTO;
import com.example.userstasks.DTO.TaskToListDTO;
import com.example.userstasks.exeption.DoesntExistsException;
import com.example.userstasks.models.Task;
import com.example.userstasks.models.User;
import com.example.userstasks.repository.TaskRepository;
import com.example.userstasks.repository.UserRepository;
import com.example.userstasks.utils.MappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRep;
    private final MappingUtils mapper;
    public TaskDTO create(Task task, Long id){
        Optional<User> u = userRep.findById(id);
        if(u.isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        task.setUser(u.get());
        return mapper.MapTask(repository.save(task));
    }
    public List<TaskToListDTO> readAll(){
        return repository.findAll().stream().map(t->mapper.MapToList(t)).collect(Collectors.toList());
    }
    public String delete(Long id, Long taskId){
        if(userRep.findById(id).isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        Optional<Task> t = repository.findById(taskId);
        if(t.isEmpty())
            throw new DoesntExistsException("Задачи с id=" + taskId + " не существует");
        repository.deleteById(taskId);
        return "Success";
    }
    public TaskToListDTO read(Long id, Long taskId)
    {
        if(userRep.findById(id).isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        Optional<Task> t = repository.findById(taskId);
        if(t.isEmpty())
            throw new DoesntExistsException("Задачи с id=" + taskId + " не существует");
        return mapper.MapToList(repository.findById(taskId).get());
    }
    public TaskToListDTO update(TaskDTO dto, Long id, Long taskId){
        if(userRep.findById(id).isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        Optional<Task> t = repository.findById(taskId);
        if(t.isEmpty())
            throw new DoesntExistsException("Задачи с id=" + taskId + " не существует");
        if(dto.getTitle()!=null)
            t.get().setTitle(dto.getTitle());
        if(dto.getStatus()!=null)
            t.get().setStatus(dto.getStatus());
        return mapper.MapToList(repository.save(t.get()));
    }
    public List<TaskToListDTO> findByUser(Long id){
        if(userRep.findById(id).isEmpty())
            throw new DoesntExistsException("Пользователя с id=" +id + " не существует");
        return repository.findAllByUser(userRep.findById(id).get()).stream().map(t->mapper.MapToList(t)).collect(Collectors.toList());
    }

}
