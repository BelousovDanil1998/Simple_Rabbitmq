package com.example.homeproducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HomeProducerApplicationTests {

	@Autowired
	private MessageSender messageSender;

	@Test
	public void testSendHome() {
		messageSender.sendHome();

		CachingConnectionFactory cachingConnectionFactory =
				new CachingConnectionFactory("localhost"); // Замените на хост и порт вашего RabbitMQ
		cachingConnectionFactory.setPort(5672);
		RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

		Message message = rabbitTemplate.receive("homeQueue", 5000);
		assertNotNull(message);

		// Десериализуйте тело сообщения обратно в Home и проверьте его содержимое.
		ObjectMapper objectMapper = new ObjectMapper();
		Home home = null;
		try {
			home = objectMapper.readValue(message.getBody(), Home.class);
		} catch (Exception e) {
			fail("Failed to deserialize the message");
		}

		assertEquals(0, home.getFloor()); // Предположим, что мы ожидаем 0, так как это первое сообщение
	}
}
