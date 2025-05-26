package planer.reminder_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReminderRequest {
    private String message;
    private LocalDateTime remindAt;
}
