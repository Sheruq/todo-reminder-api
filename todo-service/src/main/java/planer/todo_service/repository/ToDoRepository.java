package planer.todo_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import planer.todo_service.model.ToDo;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAll();
}
