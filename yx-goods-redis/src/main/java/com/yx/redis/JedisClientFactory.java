package com.yx.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClientFactory implements ClientFactory {

    private String redisUrl;

    private Integer redisPort;

    private int maxTotal = 10;

    private int maxIdle = 2;

    private JedisPool jedisPool;

    @Override
    public Jedis getClient() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setTestWhileIdle(true);
        config.setMinEvictableIdleTimeMillis(60000);
        config.setTimeBetweenEvictionRunsMillis(30000);
        jedisPool = new JedisPool(config, redisUrl, redisPort);
        return jedisPool.getResource();
    }

    @Override
    public void clear() {
        if (null != jedisPool) {
            jedisPool.destroy();
        }
    }


    public String getRedisUrl() {
        return redisUrl;
    }

    public void setRedisUrl(String redisUrl) {
        this.redisUrl = redisUrl;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(Integer redisPort) {
        this.redisPort = redisPort;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }
}
