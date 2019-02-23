package com.abhi.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.abhi.model.Employee;

@Service
public class RabbitMQListener {
	
	@RabbitListener(queues="${rabbitmq.queue}",containerFactory="simpleRabbitListenerContainerFactory")
	public void receiveMessage(final Employee company, @Header(name="x-death",required=false)Map<String,Object> dlHeader)  throws Exception{
		
		if(dlHeader !=null)
			System.out.println("Retried message for"+dlHeader.get("count")+"time");
		
		System.out.println("Received message id :"+company.getId());
		System.out.println(company);
	    
	}
}