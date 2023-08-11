package sn.bayembacke.todo.service;

import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;
import sn.bayembacke.todo.payload.response.MessageResponse;

import java.util.List;

public interface TodoService {
    MessageResponse create(TodoDto todoDto);

    MessageResponse update(TodoDto todoDto) ;

    TodoDto getTodo(Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    List<TodoDto> findAll();
}
