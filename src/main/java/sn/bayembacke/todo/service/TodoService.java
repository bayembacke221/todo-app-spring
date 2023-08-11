package sn.bayembacke.todo.service;

import sn.bayembacke.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto create(TodoDto todoDto);

    TodoDto update(TodoDto todoDto);

    TodoDto findById(Long id);

    void delete(Long id);

    List<TodoDto> findAll();
}
