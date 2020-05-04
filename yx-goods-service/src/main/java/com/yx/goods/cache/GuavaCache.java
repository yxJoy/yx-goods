package com.yx.goods.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Component
public class GuavaCache extends AbstractValueAdaptingCache implements Cache {

    private com.google.common.cache.Cache<String, Object> guavaCache;

    public GuavaCache() {
        super(true);
        this.guavaCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(5, TimeUnit.MINUTES).build();
    }

    @Override
    protected Object lookup(Object o) {
        if (null == o) {
            return null;
        }
        return guavaCache.getIfPresent(o.toString());
    }

    @Override
    public String getName() {
        return GuavaCache.class.getSimpleName().toUpperCase();
    }

    @Override
    public Object getNativeCache() {
        return guavaCache;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        if (null == o) {
            return null;
        }
        try {
            return (T) guavaCache.get(o.toString(), callable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(Object o, Object o1) {
        if (null == o || null == o1) {
            return;
        }
        try {
            guavaCache.put(o.toString(), o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        ValueWrapper valueWrapper = get(o);
        if (null == valueWrapper) {
            put(o, o1);
            return null;
        } else {
            return toValueWrapper(valueWrapper);
        }
    }

    @Override
    public void evict(Object o) {
        if (null != o) {
            guavaCache.invalidate(o.toString());
        }
    }

    @Override
    public void clear() {
        guavaCache.cleanUp();
    }
}
