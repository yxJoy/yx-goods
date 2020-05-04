package com.yx.kafka.consumer;

import com.yx.kafka.message.Message;

public interface MessageListener {

    void onMessage(Message message) throws Exception;

}