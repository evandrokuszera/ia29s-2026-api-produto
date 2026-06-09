package br.edu.utfpr.apicloudamqptest.consumer;

import br.edu.utfpr.apicloudamqptest.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectErrorConsumer {

    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_ERROR)
    public void consume(String message){
        System.out.println("DirectErrorConsumer.consume: " + message);
    }

}
