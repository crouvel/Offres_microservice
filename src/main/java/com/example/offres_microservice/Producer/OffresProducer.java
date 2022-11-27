package com.example.offres_microservice.Producer;

import com.example.offres_microservice.dto.OffresEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OffresProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffresProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, OffresEvent> kafkaTemplate;

    public OffresProducer(NewTopic topic, KafkaTemplate<String, OffresEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OffresEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<OffresEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
