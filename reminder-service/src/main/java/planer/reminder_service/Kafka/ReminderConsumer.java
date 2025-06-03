package planer.reminder_service.Kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import planer.common.dto.ReminderKafkaRequest;
import planer.reminder_service.dto.ReminderRequest;
import planer.reminder_service.service.ReminderService;

@Component
public class ReminderConsumer {

    private final ObjectMapper objectMapper;
    private final ReminderService reminderService;

    // Інжектимо вже налаштований ObjectMapper
    public ReminderConsumer(ReminderService reminderService, ObjectMapper objectMapper) {
        this.reminderService = reminderService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${topic.name}", groupId = "todo-group")
    public void listen(String message) {
        try {
            ReminderKafkaRequest kafkaReq = objectMapper.readValue(message, ReminderKafkaRequest.class);
            ReminderRequest req = new ReminderRequest();
            req.setMessage(kafkaReq.getMessage());
            req.setRemindAt(kafkaReq.getRemindAt());
            reminderService.create(req);
            System.out.println("✅ Reminder created from Kafka");
        } catch (Exception e) {
            System.err.println("❌ Failed to parse Kafka message: " + e.getMessage());
        }
    }
}
