package sn.bayembacke.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;
import sn.bayembacke.todo.payload.response.MessageResponse;
import sn.bayembacke.todo.service.TodoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.create(todoDto));
    }
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.update(todoDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) throws ResourceNotFoundException {
        todoService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Todo deleted successfully"));
    }
}
