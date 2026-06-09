package br.edu.utfpr.apicloudamqptest.controller;

import br.edu.utfpr.apicloudamqptest.dtos.RequestDTO;
import br.edu.utfpr.apicloudamqptest.producer.DirectDTOProducer;
import br.edu.utfpr.apicloudamqptest.producer.DirectProducer;
import br.edu.utfpr.apicloudamqptest.producer.SimpleProducer;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private SimpleProducer simpleProducer;
    private DirectProducer directProducer;
    private DirectDTOProducer directDTOProducer;

    public TestController(SimpleProducer simpleProducer, DirectProducer directProducer, DirectDTOProducer directDTOProducer) {
        this.simpleProducer = simpleProducer;
        this.directProducer = directProducer;
        this.directDTOProducer = directDTOProducer;
    }

    @GetMapping()
    public String simpleMessage(@RequestParam String message){
        simpleProducer.send(message);
        return "Simple Message Send.";
    }

    @GetMapping("/direct")
    public String directMessage(@RequestParam String routingKey, @RequestParam String message){
        this.directProducer.send(message, routingKey);
        return "DirectMessage.send: OK";
    }

    @PostMapping()
    public String directDTOMessage(@RequestBody RequestDTO requestDTO){
        this.directDTOProducer.send(requestDTO, "cpf");
        return "DirectDTOMessage.send: OK";
    }

}
