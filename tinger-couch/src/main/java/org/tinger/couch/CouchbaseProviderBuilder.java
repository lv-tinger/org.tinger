package org.tinger.couch;

import org.tinger.cache.CacheProviderBuilder;
import org.tinger.core.cache.CacheDriver;
import org.tinger.core.cache.CacheProvider;

/**
 * Created by tinger on 2022-11-10
 */
public class CouchbaseProviderBuilder implements CacheProviderBuilder {
    @Override
    public CacheDriver driver() {
        return CacheDriver.CBS;
    }

    @Override
    public CacheProvider build(String name) {
        return null;
    }
}