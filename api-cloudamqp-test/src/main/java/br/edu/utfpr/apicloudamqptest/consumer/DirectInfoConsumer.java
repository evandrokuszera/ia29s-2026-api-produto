package br.edu.utfpr.apicloudamqptest.consumer;

import br.edu.utfpr.apicloudamqptest.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectInfoConsumer {

    @RabbitListener(queues = DirectConfig.DIRECT_QUEUE_INFO)
    public void consume(String message){
        System.out.println("DirectInfoConsumer.consume: " + message);
    }

}
