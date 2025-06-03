package planer.todo_service.Kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import planer.common.dto.ReminderKafkaRequest;

@Service
public class ReminderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${topic.name}")
    private String topicName;

    // Інжектимо вже налаштований ObjectMapper
    public ReminderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendReminder(ReminderKafkaRequest req) {
        try {
            String json = objectMapper.writeValueAsString(req);
            kafkaTemplate.send(topicName, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

