package com.annuel.project.server.ampq;


import com.annuel.project.server.model.Matches;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;


    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;

    }



    @PostMapping("/message")
    public String sendMatchs(@RequestBody Matches matches){

        log.info("message send : {}", matches.toString() );

        rabbitTemplate.convertAndSend(AmqpConfig.QUEUE_NAME,matches);

        return "Practical tip sent";
    }




}

