package planer.reminder_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import planer.reminder_service.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
