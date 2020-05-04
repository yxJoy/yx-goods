package com.yx.kafka.consumer;

import com.yx.kafka.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private static String topic;

    private static MessageListener listener;

    private String serverAddress;

    private static KafkaConsumer<String, String> consumer;

    private static volatile Properties properties;

    MessageConsumer(String serverAddress, String topic, MessageListener listener) {
        this.topic = topic;
        this.listener = listener;

        properties = new Properties();
        //建立连接broker的地址
        properties.put("bootstrap.servers", serverAddress);
        //kafka反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //指定消费者组
        properties.put("group.id", "group-1");
        // 配置zookeeper连接超时间隔
        properties.put("zookeeper.session.timeout.ms", "10000");
        // 配置zookeeper异步执行时间
        properties.put("zookeeper.sync.time.ms", "200");
        // 配置自动提交时间间隔
        properties.put("auto.commit.interval.ms", "1000");

        doConsume();
    }


    private void doConsume() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                generalConsumeMessageAutoCommit(properties);
            }
        }).start();
    }


    //自动提交位移：由consume自动管理提交
    private static void generalConsumeMessageAutoCommit(Properties properties) {
        //配置
        properties.put("enable.auto.commit", true);
        consumer = new KafkaConsumer<>(properties);
        //指定topic
        consumer.subscribe(Collections.singleton(topic));
        try {
            while (true) {
                //拉取信息，超时时间100ms
                ConsumerRecords<String, String> records = consumer.poll(3000);
                //遍历打印消息
                for (ConsumerRecord<String, String> record : records) {
                    Message message = new Message();
                    message.setTopic(record.topic());
                    message.setKey(record.key());
                    message.setText(record.value());
                    listener.onMessage(message);
                    //消息发送完成
                    if (record.value().equals("done")) {
                        // msg done
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("consumer mq error", e);
        }finally {
//            consumer.close();
        }
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MessageListener getListener() {
        return listener;
    }

    public void setListener(MessageListener listener) {
        this.listener = listener;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
