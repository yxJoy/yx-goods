package com.yx.goods.cache;

import com.yx.redis.JedisClientFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import redis.clients.jedis.Jedis;

@Log4j2
public class RedisClientFactoryBean extends JedisClientFactory implements FactoryBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        super.clear();
    }

    @Override
    public Object getObject() throws Exception {
        return this.getClient();
    }

    @Override
    public Class<?> getObjectType() {
        return Jedis.class;
    }
}
