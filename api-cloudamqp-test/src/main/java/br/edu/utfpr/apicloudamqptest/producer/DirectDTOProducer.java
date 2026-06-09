package br.edu.utfpr.apicloudamqptest.producer;

import br.edu.utfpr.apicloudamqptest.config.DirectConfig;
import br.edu.utfpr.apicloudamqptest.dtos.RequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class DirectDTOProducer {

    private RabbitTemplate rabbitTemplate;

    public DirectDTOProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(RequestDTO requestDTO, String routingKey){
        this.rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, "cpf", requestDTO);
    }
}
