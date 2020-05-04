package com.yx.goods.cache;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@SofaService(interfaceType = CacheManager.class)
public class GoodsCacheManager implements CacheManager, InitializingBean {

    @Autowired
    private GuavaCache guavaCache;

    private final Map<String, Cache> caches = Maps.newConcurrentMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        caches.put(CacheConstant.GUAVA_NAME, guavaCache);
    }

    @Override
    public Cache getCache(String cacheName) {
        return caches.get(cacheName);
    }

    @Override
    public Collection<String> getCacheNames() {
        return caches.keySet();
    }
}
