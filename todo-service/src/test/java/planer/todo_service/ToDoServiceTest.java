package planer.todo_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import planer.todo_service.dto.TodoRequest;
import planer.todo_service.model.ToDo;
import planer.todo_service.repository.ToDoRepository;
import planer.todo_service.service.ToDoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class ToDoServiceTest {


    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Ініціалізує @Mock та @InjectMocks
    }

    @Test
    void testGetAll_ReturnsListOfTodos() {
        List<ToDo> todos = List.of(
                new ToDo(1L, "Task 1", "Desc 1", LocalDateTime.now(), false),
                new ToDo(2L, "Task 2", "Desc 2", LocalDateTime.now(), true)
        );

        when(toDoRepository.findAll()).thenReturn(todos);

        List<ToDo> result = toDoService.getAll();

        assertEquals(2, result.size());
        verify(toDoRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateTask() {
        // GIVEN
        Long id = 1L;

        ToDo existing = new ToDo();
        existing.setId(id);
        existing.setTitle("Old title");
        existing.setDescription("Old desc");
        existing.setDueDate(LocalDate.of(2025, 1, 1).atStartOfDay());
        existing.setCompleted(false);

        TodoRequest request = new TodoRequest(
                "New title",
                "New desc",
                LocalDate.of(2025, 6, 1),
                true
        );

        when(toDoRepository.findById(id)).thenReturn(Optional.of(existing));
        when(toDoRepository.save(existing)).thenReturn(existing);
        ToDo updated = toDoService.updateTask(id, request);


        // THEN
        assertEquals("New title", updated.getTitle());
        assertEquals("New desc", updated.getDescription());
        assertEquals(LocalDate.of(2025, 6, 1).atStartOfDay(), updated.getDueDate());
        assertTrue(updated.isCompleted());

        verify(toDoRepository).findById(id);
        verify(toDoRepository).save(existing);


    }
}