package br.edu.utfpr.apicloudamqptest.producer;

import br.edu.utfpr.apicloudamqptest.config.DirectConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DirectProducer {

    private RabbitTemplate rabbitTemplate;

    public DirectProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message, String routingKey){
        this.rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, routingKey, message);
        System.out.println("DirectProducer.send: " + message);
    }
}
