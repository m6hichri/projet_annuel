package com.annuel.project.server.ampq;



import com.annuel.project.server.model.Matches;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessage{

    @RabbitListener(queues = AmqpConfig.QUEUE_NAME)
    public void consumeDefaultMessage(final Matches matches) {

        log.info("message receive: {}", matches.toString() );

    }

}







