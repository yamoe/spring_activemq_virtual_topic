package com.example.demo.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {
	@JmsListener(destination = "VirtualTopic.vMailbox", containerFactory = "activeMqTopicFactory")
	public void receiveVTopic(Email email) {
		log.info("VTOPIC <" + email + ">");
	}

	@JmsListener(destination = "Consumers.QQQ.VirtualTopic.vMailbox", containerFactory = "activeMqQueueFactory")
	public void receiveVQueue(Email email) {
		log.info("VQUEUE <" + email + ">");
	}

}
