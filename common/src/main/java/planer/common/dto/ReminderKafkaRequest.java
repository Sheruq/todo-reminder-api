package planer.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReminderKafkaRequest {
    private String message;
    private LocalDateTime remindAt;
}
