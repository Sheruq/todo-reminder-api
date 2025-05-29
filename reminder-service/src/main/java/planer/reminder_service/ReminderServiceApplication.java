package planer.reminder_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import planer.reminder_service.model.Reminder;
import planer.reminder_service.repository.ReminderRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class ReminderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ReminderRepository repo) {
		return args -> {
			Reminder r = new Reminder();
			r.setMessage("Тест");
			r.setRemindAt(LocalDateTime.now().plusDays(1));
			repo.save(r);
		};
	}

}
