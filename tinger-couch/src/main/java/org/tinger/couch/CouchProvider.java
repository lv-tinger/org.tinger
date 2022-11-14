package org.tinger.couch;

import org.tinger.core.cache.CacheProvider;
import org.tinger.core.common.Executor;

import java.util.Date;

/**
 * Created by tinger on 2022-11-09
 */
public class CouchProvider implements CacheProvider {

    @Override
    public boolean ex(String key) {
        return false;
    }

    @Override
    public boolean nx(String key) {
        return false;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public void put(String key, Object value) {

    }

    @Override
    public void put(String key, Object value, int expiry) {

    }

    @Override
    public void put(String key, Object value, Date expiry) {

    }

    @Override
    public void exp(String key, int expiry) {

    }

    @Override
    public void exp(String key, Date date) {

    }

    @Override
    public void del(String key) {

    }

    @Override
    public long incr(String key, long initial, int step, int expiry) {
        return 0;
    }

    @Override
    public long decr(String key, long initial, int step, int expiry) {
        return 0;
    }

    @Override
    public void lock(String key, int timeout, int retry, Executor executor) {

    }
}
