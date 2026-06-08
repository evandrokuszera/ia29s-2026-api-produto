package br.edu.utfpr.apicloudamqptest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    public static final String SIMPLE_QUEUE = "simple.queue";

    @Bean
    public Queue simpleQueue(){
        return new Queue(SIMPLE_QUEUE, true, false, false);
    }

}
