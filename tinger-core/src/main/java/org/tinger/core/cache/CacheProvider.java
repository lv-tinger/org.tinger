package org.tinger.core.cache;

import org.tinger.core.common.Executor;

import java.util.Date;

/**
 * Created by tinger on 2022-11-09
 */
public interface CacheProvider {
    boolean ex(String key);

    boolean nx(String key);

    <T> T get(String key);

    void put(String key, Object value);

    void put(String key, Object value, int expiry);

    void put(String key, Object value, Date expiry);

    void exp(String key, int expiry);

    void exp(String key, Date date);

    void del(String key);

    long incr(String key, long initial, int step, int expiry);

    long decr(String key, long initial, int step, int expiry);

    void lock(String key, int timeout, int retry, Executor executor);
}
