package com.example.homeproducer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeProducerApplication.class, args);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("homeExchange");
	}

	@Bean
	public Queue myQueue() {
		return new Queue("homeQueue", false);
	}

	@Bean
	public Binding binding(Queue myQueue, DirectExchange exchange) {
		return BindingBuilder.bind(myQueue).to(exchange).with("home.create");
	}
}
