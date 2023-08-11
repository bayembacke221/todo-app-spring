package sn.bayembacke.todo.service;

import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;

import java.util.List;

public interface TodoService {
    TodoDto create(TodoDto todoDto);

    TodoDto update(TodoDto todoDto);

    TodoDto findById(Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    List<TodoDto> findAll();
}
