package br.edu.utfpr.apicloudamqptest.consumer;

import br.edu.utfpr.apicloudamqptest.config.SimpleConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleConsumer {

    @RabbitListener(queues = SimpleConfig.SIMPLE_QUEUE)
    public void receive1(String message) throws InterruptedException {
        System.out.println("SimpleConsumer.receive: " + message + "(" + LocalDateTime.now() + ")");
        System.out.println("SimpleConsumer.processando: " + message + " ...");
        Thread.sleep(5000);
        System.out.println("SimpleConsumer.processando: " + message + " ... OK");
    }

}
