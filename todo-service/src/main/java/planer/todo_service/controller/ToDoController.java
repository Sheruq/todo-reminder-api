package planer.todo_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planer.todo_service.dto.TodoRequest;
import planer.todo_service.model.ToDo;
import planer.todo_service.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAll() {
        return toDoService.getAll();
    }

    @PostMapping
    public ResponseEntity<ToDo> create(@RequestBody TodoRequest request){
        return ResponseEntity.ok(toDoService.createTask(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        toDoService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateTask(@PathVariable Long id, @RequestBody TodoRequest request){
        return ResponseEntity.ok(toDoService.updateTask(id, request));
    }
}
