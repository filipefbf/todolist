package br.com.desafio.todolist.controller;

import static org.mockito.Mockito.when;

import br.com.desafio.todolist.entity.Todo;
import br.com.desafio.todolist.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TodoController.class})
@ExtendWith(SpringExtension.class)
class TodoControllerTest {
  @Autowired
  private TodoController todoController;

  @MockBean
  private TodoService todoService;

  @Test
  void testList() throws Exception {
    when(todoService.list()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/todos");
    MockMvcBuilders.standaloneSetup(todoController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  @Test
  void testUpdate() throws Exception {
    when(todoService.update(Mockito.<Long>any(), Mockito.<Todo>any())).thenReturn(new ArrayList<>());

    Todo todo = new Todo();
    todo.setDescription("The characteristics of someone or something");
    todo.setDone(true);
    todo.setId(1L);
    todo.setName("Name");
    todo.setPriority(1);
    String content = (new ObjectMapper()).writeValueAsString(todo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/todos/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    MockMvcBuilders.standaloneSetup(todoController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  @Test
  void testCreate() throws Exception {
    when(todoService.list()).thenReturn(new ArrayList<>());

    Todo todo = new Todo();
    todo.setDescription("The characteristics of someone or something");
    todo.setDone(true);
    todo.setId(1L);
    todo.setName("Name");
    todo.setPriority(1);
    String content = (new ObjectMapper()).writeValueAsString(todo);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);
    MockMvcBuilders.standaloneSetup(todoController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
  @Test
  void testDelete() throws Exception {
    when(todoService.delete(Mockito.<Long>any())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/todos/{id}", 1L);
    MockMvcBuilders.standaloneSetup(todoController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
