package com.example.demo.activemq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final JmsTemplate jmsTemplate;


    public void sendVirtualTopic(String email, String msg) {
        var vo = new Email(email, msg, System.currentTimeMillis());
        jmsTemplate.convertAndSend(
            new ActiveMQTopic("VirtualTopic.vMailbox"),
            vo
        );
        log.info("email : " + email);
        log.info("message : " + msg);
        log.info("Sending an virtual topic email message: {}", vo);
    }
}

