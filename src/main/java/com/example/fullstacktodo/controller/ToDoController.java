package com.example.fullstacktodo.controller;

import com.example.fullstacktodo.dto.CreateToDoDto;
import com.example.fullstacktodo.dto.ToDoDto;
import com.example.fullstacktodo.dto.UpdateToDoDto;
import com.example.fullstacktodo.sevice.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController (ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("")
    public ResponseEntity<ToDoDto> createToD(@RequestBody CreateToDoDto newToDo) {
        ToDoDto toDoDTO = toDoService.createTodo(newToDo);
        return new ResponseEntity<> (toDoDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<ToDoDto> getToDo(@RequestParam Optional<Boolean> completed){
        if (completed.isPresent()){
            return toDoService.getToDo(completed.get());
        }
        return toDoService.getToDo();
    }

    @GetMapping("/id")
    public ToDoDto getToDoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDoDto updateToDo(@PathVariable Long id, @RequestBody UpdateToDoDto updateToDoDto) {
        return toDoService.updateToDo(id, updateToDoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
