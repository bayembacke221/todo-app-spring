package sn.bayembacke.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.bayembacke.todo.be.Todo;
import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;
import sn.bayembacke.todo.payload.mapper.TodoDtoMapper;
import sn.bayembacke.todo.payload.response.MessageResponse;
import sn.bayembacke.todo.repository.TodoRepository;
import sn.bayembacke.todo.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    TodoDtoMapper todoDtoMapper;
    @Override
    public MessageResponse create(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setTitle(todoDto.title());
        todo.setDescription(todoDto.description());
        todo.setDone(todoDto.done());
        todoRepository.save(todo);
        return new MessageResponse("Todo created successfully");
    }

    @Override
    public MessageResponse update(TodoDto todoDto) {
        Todo todo = todoRepository.findById(todoDto.id())
                .orElse(null);
        if (todo != null) {
            todo.setTitle(todoDto.title());
            todo.setDescription(todoDto.description());
            todo.setDone(todoDto.done());
            todoRepository.save(todo);
             todoDtoMapper.apply(todo);
            return new MessageResponse("Todo updated successfully");
        }
        return new MessageResponse("Todo not found");
    }

    @Override
    public TodoDto getTodo(Long id) throws ResourceNotFoundException {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        return null;
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
        todoRepository.delete(todo);

    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream()
                .map(todoDtoMapper)
                .collect(Collectors.toList());
    }
}
