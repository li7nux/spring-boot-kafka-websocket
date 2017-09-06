package com.craftcoder.chcp.kafka.listener;

import com.craftcoder.chcp.util.ChcpConstants;
import com.craftcoder.chcp.util.JsonHelper;
import com.craftcoder.chcp.websocket.service.WebsocketService;
import com.craftcoder.chcp.websocket.vo.Message;
import com.craftcoder.chcp.websocket.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @Autowired
    private WebsocketService websocketService;

    @KafkaListener(topics = ChcpConstants.Constants.KAFKA_LISTENER_TOPIC)
    public void consumer(String message) {

        logger.info("consumer topic string get : {}", message);

        Message messageReq = new Message();
        messageReq.setMessage(message);

        logger.info("send websocket request : {}", JsonHelper.toJson(messageReq).toString());

        Response response = websocketService.send(messageReq);

        logger.info("send websocket response : {}", JsonHelper.toJson(response).toString());

    }

}
