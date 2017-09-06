package com.craftcoder.chcp.kafka.controller;

import com.craftcoder.chcp.util.ChcpConstants;
import com.craftcoder.chcp.websocket.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/message/send")
    public String sendMessage(@RequestBody Message message) {
        kafkaTemplate.send(ChcpConstants.KAFKA_LISTENER_TOPIC.getTopic(), message.getMessage());
        return message.getMessage();
    }


}
