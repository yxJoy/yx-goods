package com.yx.kafka.producer;


import com.yx.kafka.exception.KafkaMqException;
import com.yx.kafka.message.Message;

import java.util.List;

public interface Producer {

    void send(Message message) throws KafkaMqException;

    void send(Message message, int timeout) throws KafkaMqException;

    void send(List<Message> messages, int timeout) throws KafkaMqException;

    void send(List<Message> messages, int timeout, boolean isAsync) throws KafkaMqException;

    void send(Message message, AsyncSendCallback callback) throws KafkaMqException;

    void send(Message message, int timeout, AsyncSendCallback callback) throws KafkaMqException;

    void send(List<Message> messages, AsyncSendCallback callback) throws KafkaMqException;

    void send(List<Message> messages, int timeout, AsyncSendCallback callback) throws KafkaMqException;
}
