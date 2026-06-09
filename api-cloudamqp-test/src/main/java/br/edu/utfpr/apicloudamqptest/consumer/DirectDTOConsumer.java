package br.edu.utfpr.apicloudamqptest.consumer;

import br.edu.utfpr.apicloudamqptest.config.DirectConfig;
import br.edu.utfpr.apicloudamqptest.dtos.ResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectDTOConsumer {

    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_CPF)
    public void consume(ResponseDTO responseDTO){
        System.out.println("DirectDTOConsumer.consume: " + responseDTO);
    }

}
