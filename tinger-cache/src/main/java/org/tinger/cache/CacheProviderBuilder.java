package org.tinger.cache;

import org.tinger.core.cache.CacheDriver;
import org.tinger.core.cache.CacheProvider;

/**
 * Created by tinger on 2022-11-09
 */
public interface CacheProviderBuilder {
    CacheDriver driver();

    CacheProvider build(String name);
}
