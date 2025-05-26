package planer.reminder_service.service;

import org.springframework.stereotype.Service;
import planer.reminder_service.dto.ReminderRequest;
import planer.reminder_service.dto.ReminderResponse;
import planer.reminder_service.model.Reminder;
import planer.reminder_service.repository.ReminderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final ReminderRepository repo;
    public ReminderService(ReminderRepository repo) { this.repo = repo; }

    public List<ReminderResponse> getAll() {
        return repo.findAll().stream()
                .map(r -> new ReminderResponse(r.getId(), r.getMessage(), r.getRemindAt()))
                .collect(Collectors.toList());
    }

    public ReminderResponse getById(Long id) {
        Reminder r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        return new ReminderResponse(r.getId(), r.getMessage(), r.getRemindAt());
    }

    public ReminderResponse create(ReminderRequest req) {
        Reminder r = new Reminder();
        r.setMessage(req.getMessage());
        r.setRemindAt(req.getRemindAt());
        Reminder saved = repo.save(r);
        return new ReminderResponse(saved.getId(), saved.getMessage(), saved.getRemindAt());
    }

    public ReminderResponse update(Long id, ReminderRequest req) {
        Reminder r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));
        r.setMessage(req.getMessage());
        r.setRemindAt(req.getRemindAt());
        Reminder saved = repo.save(r);
        return new ReminderResponse(saved.getId(), saved.getMessage(), saved.getRemindAt());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
