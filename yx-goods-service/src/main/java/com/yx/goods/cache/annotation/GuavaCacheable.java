package com.yx.goods.cache.annotation;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Cacheable
@Documented
@Inherited
public @interface GuavaCacheable {

    @AliasFor("cacheNames")
    String[] value() default {"GUAVACACHE"};

    @AliasFor("value")
    String[] cacheNames() default {"GUAVACACHE"};

    String key() default "";

    String keyGenerator() default "";

    String condition() default "";

    String unless() default "";
}
