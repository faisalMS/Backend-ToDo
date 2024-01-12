package com.example.fullstacktodo.sevice;

import com.example.fullstacktodo.dto.CreateToDoDto;
import com.example.fullstacktodo.dto.ToDoDto;
import com.example.fullstacktodo.dto.UpdateToDoDto;
import com.example.fullstacktodo.exception.ToDoException;
import com.example.fullstacktodo.model.ToDo;
import com.example.fullstacktodo.respository.ToDoRespository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private ToDoRespository toDoRespository;

    public ToDoService (ToDoRespository toDoRespository){
        this.toDoRespository = toDoRespository;
    }

    public ToDoDto createTodo(CreateToDoDto createToDoDto){
        ToDo newToDo = new ToDo();
        newToDo.setName(createToDoDto.getName());
        newToDo.setCompleted(createToDoDto.getCompleted());
        ToDo toDo = toDoRespository.save(newToDo);
        return new ToDoDto(toDo);
    }

    public List<ToDoDto> getToDo(){
        List<ToDo> toDo = toDoRespository.findAll();
        return toDo.stream().map(entity -> new ToDoDto(entity)).toList();
    }

    public List<ToDoDto> getToDo(Boolean completed) {
        List<ToDo> todo = toDoRespository.findByCompleted(completed);
        return todo.stream().map(entity -> new ToDoDto(entity)).toList();
    }

    public ToDoDto getToDoById(Long id) {
        Optional<ToDo> toDo = toDoRespository.findById(id);
        if (toDo.isPresent()){
            return new ToDoDto(toDo.get());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "GetToDoById - to do not found"
            );
        }
    }

    public ToDoDto updateToDo(Long id, UpdateToDoDto updateToDo){
        Optional<ToDo> toDo = toDoRespository.findById(id);
        if (toDo.isPresent()){
            toDo.get().setName(updateToDo.getName());
            toDo.get().setCompleted(updateToDo.getCompleted());
            toDoRespository.save(toDo.get());
            return new ToDoDto(toDo.get());
        } else {
            throw new ToDoException(404, "UpdateToDo - to do not found");
        }
    }

    public void deleteToDo(Long id) {
        Optional<ToDo> toDo = toDoRespository.findById(id);
        if (toDo.isPresent()){
            toDoRespository.delete(toDo.get());
        } else {
            throw new RuntimeException("DeleteToDo - to do not found");
        }
    }
}
