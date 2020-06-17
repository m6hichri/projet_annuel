package com.annuel.project.server.ampq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

public class ReceiveMessageHandler {

    public void handleMessage(Message messageBody) {
        System.out.println("*************mounaaaaa***********"+messageBody );
        //log.info(messageBody);
    }
}
