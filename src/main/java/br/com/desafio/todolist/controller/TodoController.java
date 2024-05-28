package br.com.desafio.todolist.controller;

import br.com.desafio.todolist.entity.Todo;
import br.com.desafio.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //create
    @PostMapping
    public List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    //list
    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    //update
    @PutMapping("{id}")
    public List<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.update(todo);
    }

    //delete
    @DeleteMapping("{id}")
    public List<Todo> delete(@PathVariable("id") Long id){
        return todoService.delete(id);
    }
}
