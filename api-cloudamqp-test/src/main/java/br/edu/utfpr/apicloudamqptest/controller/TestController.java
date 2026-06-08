package br.edu.utfpr.apicloudamqptest.controller;

import br.edu.utfpr.apicloudamqptest.producer.SimpleProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private SimpleProducer simpleProducer;

    public TestController(SimpleProducer simpleProducer) {
        this.simpleProducer = simpleProducer;
    }

    @GetMapping()
    public String simpleMessage(@RequestParam String message){
        simpleProducer.send(message);
        return "Simple Message Send.";
    }

}
