package com.craftcoder.chcp.util;

/**
 * kafka 和 websocket 监听发送主题定义常量类
 */
public enum ChcpConstants {

    KAFKA_LISTENER_TOPIC(Constants.KAFKA_LISTENER_TOPIC, "kafka 消费者监听主题"),
    WEBSOCKET_DESTINATION("/topic/getResponse", "websocket 发送目标");

    private String topic;
    private String desc;

    ChcpConstants(String topic, String desc) {
        this.setTopic(topic);
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Constants {
        public static final String KAFKA_LISTENER_TOPIC = "topic-string";
    }

}
