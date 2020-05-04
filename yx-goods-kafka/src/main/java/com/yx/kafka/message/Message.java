package com.yx.kafka.message;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    public static String EXPIRE = "EXPIRE";
    protected short queueId;
    protected String topic;
    protected String key;
    protected String businessId;
    protected byte priority;
    protected boolean ordered;
    protected String text;
    protected byte[] body;
    protected Map<String, String> attributes;
    protected long sendTime;

    public Message() {
    }

    public Message(String topic, String text, String businessId, String key) {
        this.setTopic(topic);
        this.setBusinessId(businessId);
        this.setText(text);
        this.setKey(key);
    }

    public Message topic(String topic) {
        this.setTopic(topic);
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Message businessId(String businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public Message priority(byte priority) {
        this.setPriority(priority);
        return this;
    }

    public Message ordered(boolean ordered) {
        this.setOrdered(ordered);
        return this;
    }

    public Message text(String text) {
        this.setText(text);
        return this;
    }

    public Message body(byte[] data) {
        this.setBody(data, 0, data.length);
        return this;
    }

    public Message attribute(String key, String value) {
        this.setAttribute(key, value);
        return this;
    }

    public Message attributes(Map<String, String> attributes) {
        this.setAttributes(attributes);
        return this;
    }

    public Message queueId(short queueId) {
        this.setQueueId(queueId);
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public byte getPriority() {
        return this.priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public long getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public short getQueueId() {
        return this.queueId;
    }

    public void setQueueId(short queueId) {
        this.queueId = queueId;
    }

    public byte[] getByteBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setBody(byte[] data, int offset, int length) {
        if (offset == 0 && length == data.length) {
            this.setBody(data);
        } else {
            int srcLen = data.length;
            int remain = length;
            if (length > srcLen - offset) {
                remain = srcLen - offset;
            }

            byte[] dest = new byte[remain];
            System.arraycopy(data, offset, dest, 0, remain);
            this.setBody(dest);
        }

    }

    public String getText() {
        if (this.body != null && this.body.length != 0) {
            if (this.text == null) {
                this.text = new String(this.body, Charset.forName("UTF-8"));
            }
            return this.text;
        } else {
            return null;
        }
    }

    public void setText(String text) {
        this.text = text;
        byte[] data;
        if (text == null) {
            data = new byte[0];
        } else {
            data = text.getBytes(Charset.forName("UTF-8"));
        }

        if (data.length >= 100) { // todo compress ontent
        }

        this.body = data;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(String key) {
        return this.attributes == null ? null : (String)this.attributes.get(key);
    }

    public void setAttribute(String key, String value) {
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }

        this.attributes.put(key, value);
    }

    public String getTags() {
        return this.getAttribute("TAGS");
    }

    public void setTags(String tags) {
        this.setAttribute("TAGS", tags);
    }

    public long getExpire() {
        String value = this.getAttribute(EXPIRE);
        return value == null ? 0L : Long.valueOf(value);
    }

    public void setExpire(long expire) {
        this.setAttribute(EXPIRE, String.valueOf(expire));
    }

    public int getSize() {
        if (this.body != null) {
            return this.body.length;
        } else if (this.text != null) {
            byte[] bytes = this.text.getBytes(Charset.forName("UTF-8"));
            return bytes.length;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", key='" + key + '\'' +
                ", businessId='" + businessId + '\'' +
                ", priority=" + priority +
                ", ordered=" + ordered +
                ", text='" + text + '\'' +
                ", attributes=" + attributes +
                ", sendTime=" + sendTime +
                '}';
    }
}
