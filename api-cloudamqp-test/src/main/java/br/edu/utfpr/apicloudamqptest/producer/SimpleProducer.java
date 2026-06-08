package br.edu.utfpr.apicloudamqptest.producer;

import br.edu.utfpr.apicloudamqptest.config.SimpleConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleProducer {

    private final RabbitTemplate rabbitTemplate;

    public SimpleProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message){
        this.rabbitTemplate.convertAndSend(SimpleConfig.SIMPLE_QUEUE, message);
        System.out.println("SimpleProducer.send: " + message);
    }
}