# 📝 To-Do Reminder API

Це мікросервісний REST API для керування завданнями (to-do) з можливістю надсилати нагадування через Kafka. Проєкт реалізований на Java (Spring Boot), PostgreSQL, Docker і Kafka, та призначений для демонстрації реальної взаємодії мікросервісів у портфоліо.

## ⚙️ Стек технологій

- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA
- Apache Kafka
- PostgreSQL
- Docker & Docker Compose
- Maven
- ObjectMapper + Jackson (з підтримкою `JavaTimeModule`)

## 🧩 Архітектура

```
                           +------------------+
                           |  todo-service    |
                           |------------------|
                           |  REST API        |
                           |  Kafka Producer  |
                           +------------------+
                                   |
                            Kafka (reminder-topic)
                                   |
                           +---------------------+
                           |  reminder-service   |
                           |---------------------|
                           |  Kafka Consumer     |
                           |  Reminder Logic     |
                           +---------------------+
```

- `todo-service`: створює задачі та надсилає повідомлення у Kafka.
- `reminder-service`: слухає Kafka та створює нагадування.

## 🗂 Структура проєкту

```
todo-reminder-api/
├── common/                # Спільні DTO та конфігурації (Jackson)
├── todo-service/          # Сервіс для задач (ToDo)
├── reminder-service/      # Сервіс нагадувань
├── docker-compose.yml     # Підняття Kafka, PostgreSQL, мікросервісів
```

## 🚀 Як запустити

### Попередні умови

- Docker + Docker Compose
- Java 17
- Maven

### Запуск у Docker:

```bash
docker-compose up --build
```

Після запуску:
- ToDo-сервіс доступний на `http://localhost:8080`
- Reminder-сервіс — внутрішній, Kafka слухає topic `reminder-topic`.

## 🔄 Приклад взаємодії

1. ToDo-сервіс приймає POST-запит на створення завдання:
```json
{
  "message": "Подзвонити клієнту",
  "remindAt": "2025-06-05T14:00:00"
}
```

2. Kafka-повідомлення потрапляє у `reminder-topic`.

3. Reminder-сервіс слухає цей topic, десеріалізує повідомлення й створює нагадування.

## 🧪 Тестування

Для тестування Kafka можна використовувати:
- `kafka-console-producer` / `kafka-console-consumer`
- Postman (для виклику API ToDo-сервісу)
- Логи в Docker (`docker-compose logs -f`)

## 🛠 Конфігурація

**Kafka** (у `application.yml`):
```yaml
spring.kafka.bootstrap-servers: kafka:9092
topic.name: reminder-topic
```

**PostgreSQL**:
```yaml
spring.datasource.url: jdbc:postgresql://postgres:5432/todo
spring.datasource.username: user
spring.datasource.password: password
```

## 👤 Автор

**Сергій Ленів**  
📧 leniv.tech@gmail.com  
📍 Львів  
[GitHub профіль](https://github.com/Sheruq)
