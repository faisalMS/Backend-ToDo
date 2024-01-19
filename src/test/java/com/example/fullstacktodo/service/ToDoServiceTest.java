package com.example.fullstacktodo.service;

import com.example.fullstacktodo.dto.ToDoDto;
import com.example.fullstacktodo.model.ToDo;
import com.example.fullstacktodo.respository.ToDoRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @MockBean
    private ToDoRespository toDoRepository;

    @Test
    public void getToDosShouldReturnTodos() throws Exception {
        List<ToDo> todos = new ArrayList<>();
        ToDo todo = new ToDo();
        todo.setId(1L);
        todo.setName("write unit tests");
        todo.setCompleted(false);
        todos.add(todo);
        when(toDoRepository.findAll()).thenReturn(todos);
        List<ToDoDto> todoDtoList = toDoService.getToDo();
        assertThat(todoDtoList).hasSize(1);
        assertEquals(1, todoDtoList.size());
    }
}