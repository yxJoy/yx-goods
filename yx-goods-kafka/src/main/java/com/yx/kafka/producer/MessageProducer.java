package com.yx.kafka.producer;

import com.yx.kafka.exception.KafkaMqException;
import com.yx.kafka.message.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessageProducer implements Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private  static final Integer DEFAULT_TIME_OUT = 3000;

    private KafkaProducer<String, String> kafkaProducer;

    private String serverAddress;


    MessageProducer(String serverAddress) {
        Properties properties = new Properties();
        //kafka启动，生产者建立连接broker的地址
        properties.put("bootstrap.servers", serverAddress);
        //kafka序列化方式
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //自定义分区分配器
        properties.put("partitioner.class", "com.alipay.sofa.kafka.part.CustomPartitioner");
        kafkaProducer = new KafkaProducer<>(properties);
    }



    public void send(Message message) throws KafkaMqException {
        List<Message> messages = new ArrayList();
        messages.add(message);
        this.send((List)messages, DEFAULT_TIME_OUT);
    }

    public void send(Message message, int timeout) throws KafkaMqException {
        if (message != null) {
            List<Message> messages = new ArrayList();
            messages.add(message);
            this.send((List)messages, timeout);
        }
    }

    public void send(List<Message> messages, int timeout) throws KafkaMqException {
        this.send(messages, timeout, false, (AsyncSendCallback)null);
    }

    @Override
    public void send(List<Message> messages, int timeout, boolean isAsync) throws KafkaMqException {
        this.send(messages, timeout, false, (AsyncSendCallback)null);
    }

    @Override
    public void send(Message message, AsyncSendCallback callback) throws KafkaMqException {
        if (message != null) {
            List<Message> messages = new ArrayList();
            messages.add(message);
            this.send(messages, DEFAULT_TIME_OUT, (AsyncSendCallback)null);
        }
    }

    @Override
    public void send(Message message, int timeout, AsyncSendCallback callback) throws KafkaMqException {
        if (message != null) {
            List<Message> messages = new ArrayList();
            messages.add(message);
            this.send(messages, timeout, (AsyncSendCallback)null);
        }
    }

    @Override
    public void send(List<Message> messages, AsyncSendCallback callback) throws KafkaMqException {
        if (null != messages && messages.size() > 0) {
            this.send(messages, DEFAULT_TIME_OUT, callback);
        }
    }

    @Override
    public void send(List<Message> messages, int timeout, AsyncSendCallback callback) throws KafkaMqException {
        if (null != messages && messages.size() > 0) {
            this.send(messages, timeout, true, callback);
        }
    }

    private void send(List<Message> messages, int timeout, boolean isAsync, AsyncSendCallback callback) throws KafkaMqException {
        long startTime = System.nanoTime(); // todo check message
        int count = messages.size();
        int errorCount = 0;

        for (Message message : messages) {
            try {
                ProducerRecord<String, String> record = new ProducerRecord<>(message.getTopic(), message.getKey(), message.getText());
                if (isAsync) {
                    kafkaProducer.send(record, callback);
                } else {
                    RecordMetadata result = kafkaProducer.send(record).get();
                }
            } catch (Exception e) {
                errorCount++;
                LOGGER.error("send mq error msg={}", message, e);
            }
        }
        LOGGER.info("send mq cost time = {}, total count={}, fail count = {}", System.nanoTime() - startTime, count, errorCount);
    }


    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
