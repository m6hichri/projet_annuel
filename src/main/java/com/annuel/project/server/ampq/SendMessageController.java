package com.annuel.project.server.ampq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendMessageController {

    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    //public String sendMessage(@PathVariable("message") String message) {
    public String sendMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend("annuel_project_queue", message);
        return "This is a Message to send! :" + message;
    }
}