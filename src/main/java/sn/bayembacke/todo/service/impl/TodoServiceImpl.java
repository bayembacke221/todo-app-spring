package sn.bayembacke.todo.service.impl;

import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;
import sn.bayembacke.todo.service.TodoService;

import java.util.List;

public class TodoServiceImpl implements TodoService {
    @Override
    public TodoDto create(TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto update(TodoDto todoDto) {
        return null;
    }

    @Override
    public TodoDto findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {

    }

    @Override
    public List<TodoDto> findAll() {
        return null;
    }
}
