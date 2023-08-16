package sn.bayembacke.todo.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sn.bayembacke.todo.be.Todo;
import sn.bayembacke.todo.dto.TodoDto;
import sn.bayembacke.todo.exception.ResourceNotFoundException;
import sn.bayembacke.todo.payload.mapper.TodoDtoMapper;
import sn.bayembacke.todo.payload.response.MessageResponse;
import sn.bayembacke.todo.repository.TodoRepository;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TodoServiceImplTest {
    @Mock
    TodoRepository todoRepository;

    @Mock
    TodoDtoMapper todoDtoMapper;
    @InjectMocks
    TodoServiceImpl todoService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void create() {
        //GIVEN
        TodoDto todoDto = new TodoDto(1L,
                "title", "description", true);
        //WHEN
        MessageResponse messageResponse = todoService.create(todoDto);
        //THEN
        assertNotNull(messageResponse);
        assertEquals("Todo created successfully", messageResponse.getMessage());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void update() {
        //GIVEN
        Long id = 1L;
        TodoDto todoDto = new TodoDto(id,
                "title", "description", true);
        Todo existingTodo = new Todo();
        existingTodo.setId(id);

        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(existingTodo));
        //WHEN
        MessageResponse messageResponse = todoService.update(todoDto);
        //THEN
        assertNotNull(messageResponse);
        assertEquals("Todo updated successfully", messageResponse.getMessage());
        assertEquals("title", existingTodo.getTitle());
        assertEquals("description", existingTodo.getDescription());
        verify(todoRepository, times(1)).save(any(Todo.class));

    }

    @Test
    void getTodoById() throws ResourceNotFoundException {
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        when(todoDtoMapper.apply(todo)).thenReturn(new
                TodoDto(id, "title", "description", true));
        //WHEN
        TodoDto todoDto = todoService.getTodoById(id);
        //THEN
        assertNotNull(todoDto);
        assertEquals(id, todoDto.id());
        assertEquals("title", todoDto.title());
        verify(todoRepository, times(1)).findById(id);
    }

    @Test
    void delete() {
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);
        when(todoRepository.findById(id)).thenReturn(java.util.Optional.of(todo));
        //WHEN
        assertDoesNotThrow(() -> todoService.delete(id));
        //THEN
        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    void findAll() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("title");
        todo.setDescription("description");
        todo.setDone(true);
        Todo todo2 = new Todo();
        todo2.setId(2L);
        todo2.setTitle("title2");
        todo2.setDescription("description2");
        todo2.setDone(true);

        List<Todo> list = new ArrayList<>();
        list.add(todo);
        list.add(todo2);

        when(todoRepository.findAll()).thenReturn(list);
        when(todoDtoMapper.apply(todo)).thenReturn(new
                TodoDto(1L, "title", "description", true));
        when(todoDtoMapper.apply(todo2)).thenReturn(new
                TodoDto(2L, "title2", "description2", true));
        //WHEN
        List<TodoDto> todoDtoList = todoService.findAll();
        //THEN
        assertNotNull(todoDtoList);
        assertEquals(2, todoDtoList.size());
        assertEquals("title", todoDtoList.get(0).title());
        assertEquals("title2", todoDtoList.get(1).title());
        verify(todoRepository, times(1)).findAll();

    }
}