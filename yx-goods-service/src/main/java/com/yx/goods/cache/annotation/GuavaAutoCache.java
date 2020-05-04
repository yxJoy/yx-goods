package com.yx.goods.cache.annotation;

import com.yx.goods.cache.CacheConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Cacheable
@Documented
@Inherited
public @interface GuavaAutoCache {

    @AliasFor("cacheNames")
    String[] value() default {CacheConstant.GUAVA_NAME};

    @AliasFor("value")
    String[] cacheNames() default {CacheConstant.GUAVA_NAME};

    String keyGenerator() default CacheConstant.KEY_AUOTA_GEN;

    String condition() default "";

    String unless() default "";
}
