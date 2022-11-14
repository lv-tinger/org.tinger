package org.tinger.config;

import lombok.extern.slf4j.Slf4j;
import org.tinger.common.utils.ServiceLoaderUtils;
import org.tinger.core.conf.Config;
import org.tinger.core.conf.ConfigLoader;
import org.tinger.core.conf.ConfigModule;

import java.util.List;
import java.util.Map;

/**
 * Created by tinger on 2022-10-03
 */
@Slf4j
public class TingerConfigModule implements ConfigModule {

    private Config config;

    @Override
    public void install() {
        TingerConfig tingerConfig = new TingerConfig();
        config = tingerConfig;
        List<ConfigLoader> loaders = ServiceLoaderUtils.scan(ConfigLoader.class);
        for (ConfigLoader loader : loaders) {
            Map<String, Object> configValues = loader.load();
            tingerConfig.conf(configValues);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public String getName() {
        return "TINGER-CONFIG";
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Config getConfig() {
        return this.config;
    }
}
