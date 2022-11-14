package org.tinger.data.cacheable;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cacheable {
    // 缓存名称
    String name() default "default";

    // 缓存周期
    long period() default 1800;

    CacheSystem system() default CacheSystem.MEMCACHED;

    // 缓存的数据类型
    Class<?> type();
}