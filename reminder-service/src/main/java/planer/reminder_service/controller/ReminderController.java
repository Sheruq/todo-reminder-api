package planer.reminder_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planer.reminder_service.dto.ReminderRequest;
import planer.reminder_service.dto.ReminderResponse;
import planer.reminder_service.service.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private ReminderService svc;

    @Autowired
    public void setReminderService(ReminderService svc){
        this.svc = svc;
    }


    @GetMapping
    public List<ReminderResponse> all() { return svc.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderResponse> one(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReminderResponse> create(@RequestBody ReminderRequest req) {
        return ResponseEntity.ok(svc.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderResponse> update(@PathVariable Long id,
                                                   @RequestBody ReminderRequest req) {
        return ResponseEntity.ok(svc.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
