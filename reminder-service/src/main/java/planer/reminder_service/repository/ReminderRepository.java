package planer.reminder_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planer.reminder_service.model.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
