package com.yx.goods.cache;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

@Component("cacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator, Serializable {

    private static final String KEY_SPLIT = "_";

    private static final String NULL_PARAM = "null";

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getName()).append(".").append(method.getName()).append(KEY_SPLIT);
        if (params.length != 0) {
            for (Object param : params) {
                if (null == param) {
                    key.append(NULL_PARAM);
                } else if (isSimpleValueType(param.getClass())) {
                    key.append(param).append(KEY_SPLIT);
                } else {
                    key.append(JSON.toJSONString(param).hashCode()).append(KEY_SPLIT);
                }
            }
        }
        return key.toString();
    }

    private boolean isSimpleValueType(Class<?> clazz) {
        return ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || URI.class == clazz
                || URL.class == clazz || Locale.class == clazz || Class.class == clazz;
    }
}
