package org.tinger.core.cache;

import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-11-09
 */
public interface CacheModule extends Module {
    CacheProvider get(String name, CacheDriver driver);
}