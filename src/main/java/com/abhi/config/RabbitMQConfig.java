package com.abhi.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abhishek
 *
 */
@Configuration
@EnableRabbit
public class RabbitMQConfig {



	/**
	 * 
	 * This bean declaration is mandatory for declaring queues if they are not exist
	 * on MQ server
	 * 
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		
		SimpleRabbitListenerContainerFactory factory=new SimpleRabbitListenerContainerFactory();
		
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(5);
		factory.setMaxConcurrentConsumers(10);
		factory.setMessageConverter(jsonMessageConverter());
		factory.setDefaultRequeueRejected(false);
		
		return factory;
		
	}
}
