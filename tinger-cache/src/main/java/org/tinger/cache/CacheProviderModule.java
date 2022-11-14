package org.tinger.cache;

import org.tinger.common.buffer.TingerMapBuffer;
import org.tinger.common.utils.ServiceLoaderUtils;
import org.tinger.core.apps.ApplicationWeaver;
import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.cache.CacheDriver;
import org.tinger.core.cache.CacheModule;
import org.tinger.core.cache.CacheProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tinger on 2022-11-09
 */
public class CacheProviderModule extends ApplicationWeaverModule implements CacheModule {

    private final TingerMapBuffer<String, CacheProvider> buffer = new TingerMapBuffer<>();
    private final Map<CacheDriver, CacheProviderBuilder> cacheBuilder = new HashMap<>();

    @Override
    public void install() {
        List<CacheProviderBuilder> cacheProviderBuilders = ServiceLoaderUtils.scan(CacheProviderBuilder.class);
        for (CacheProviderBuilder builder : cacheProviderBuilders) {
            if (builder instanceof ApplicationWeaver) {
                ((ApplicationWeaver) buffer).weave(application);
            }
            cacheBuilder.put(builder.driver(), builder);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public CacheProvider get(String name, CacheDriver driver) {
        return buffer.get(driver.name() + "_" + name, () -> cacheBuilder.get(driver).build(name));
    }
}
