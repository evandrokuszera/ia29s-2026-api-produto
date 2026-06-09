package br.edu.utfpr.apicloudamqptest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    public static final String DIRECT_EXCHANGE = "direct.exchange";
    public static final String DIRECT_QUEUE_ERROR = "direct.queue.error";
    public static final String DIRECT_QUEUE_INFO = "direct.queue.info";
    public static final String DIRECT_QUEUE_CPF = "direct.queue.cpf";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue errorQueue(){
        return new Queue(DIRECT_QUEUE_ERROR);
    }

    @Bean
    public Queue infoQueue(){
        return new Queue(DIRECT_QUEUE_INFO);
    }

    @Bean
    public Queue cpfQueue(){
        return new Queue(DIRECT_QUEUE_CPF);
    }

    // Vinculando as filas na exchange

    @Bean
    public Binding cpfBinding(){
        return BindingBuilder.bind(cpfQueue()).to(directExchange()).with("cpf");
    }

    @Bean
    public Binding errorBinding(){
        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
    }

    @Bean
    public Binding infoBinding(){
        return BindingBuilder.bind(infoQueue()).to(directExchange()).with("info");
    }
}
