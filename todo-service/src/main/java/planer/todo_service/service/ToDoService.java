package planer.todo_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planer.todo_service.dto.TodoRequest;
import planer.todo_service.model.ToDo;
import planer.todo_service.repository.ToDoRepository;

import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    public void deleteTask(Long id) {
        try{
            toDoRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ToDo createTask(TodoRequest request) {
        ToDo toDo = new ToDo();
        toDo.setTitle(request.getTitle());
        toDo.setDescription(request.getDescription());
        toDo.setDueDate(request.getDueDate().atStartOfDay());
        toDo.setCompleted(request.isCompleted());
        return toDoRepository.save(toDo);
    }

    public ToDo updateTask(Long id, TodoRequest request) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));

        toDo.setTitle(request.getTitle());
        toDo.setDescription(request.getDescription());
        toDo.setDueDate(request.getDueDate().atStartOfDay());
        toDo.setCompleted(request.isCompleted());
        return toDoRepository.save(toDo);
    }


}
