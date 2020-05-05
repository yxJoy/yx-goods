package com.yx.redis;

import redis.clients.jedis.Jedis;

public interface ClientFactory {

    /**
     * 获取jedis客户端
     * @return
     */
    Jedis getClient();

    void clear();
}
