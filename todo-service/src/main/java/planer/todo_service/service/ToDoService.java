package planer.todo_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planer.common.dto.ReminderKafkaRequest;
import planer.todo_service.Kafka.ReminderProducer;
import planer.todo_service.dto.TodoRequest;
import planer.todo_service.model.ToDo;
import planer.todo_service.repository.ToDoRepository;

import java.util.List;

@Service
public class ToDoService {

    private ReminderProducer reminderProducer;
    private ToDoRepository toDoRepository;

    @Autowired
    public void setReminderProducer(ReminderProducer reminderProducer) {
        this.reminderProducer = reminderProducer;
    }
    @Autowired
    public void setToDoRepository(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

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

        ToDo saved = toDoRepository.save(toDo);

        // Відправляємо Kafka-нагадування
        ReminderKafkaRequest reminder = new ReminderKafkaRequest();
        reminder.setMessage("Reminder: " + saved.getTitle());
        reminder.setRemindAt(saved.getDueDate()); // або зсунь на 2 години раніше, якщо хочеш

        reminderProducer.sendReminder(reminder);

        return saved;
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
