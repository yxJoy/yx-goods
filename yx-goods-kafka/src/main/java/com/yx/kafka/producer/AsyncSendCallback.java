package com.yx.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncSendCallback implements Callback {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSendCallback.class);

    private String text; // mq报文信息

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            LOGGER.error("sendMq ERROR, metadata={}", metadata, exception);
            return;
        }
        LOGGER.info("*** MyProducerCallback *** metadata={}", metadata);
    }
}
