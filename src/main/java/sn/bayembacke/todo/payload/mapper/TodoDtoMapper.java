package sn.bayembacke.todo.payload.mapper;

import org.springframework.stereotype.Service;
import sn.bayembacke.todo.be.Todo;
import sn.bayembacke.todo.dto.TodoDto;

import java.util.function.Function;

@Service
public class TodoDtoMapper implements Function<Todo, TodoDto> {
    @Override
    public TodoDto apply(Todo todo) {
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isDone()
        );
    }
}
