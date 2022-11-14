package org.tinger.data.cacheable;

import org.tinger.common.buffer.TingerMapBuffer;
import org.tinger.common.utils.IoUtils;
import org.tinger.common.utils.ServiceLoaderUtils;
import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.conf.Config;
import org.tinger.core.conf.ConfigModule;
import org.tinger.core.data.DataModule;
import org.tinger.core.data.JdbcDriver;
import org.tinger.core.data.source.DataSourceBuilder;
import org.tinger.core.data.source.DataSourceConfig;

import javax.sql.DataSource;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TingerDataModule extends ApplicationWeaverModule implements DataModule {

    private TingerMapBuffer<JdbcDriver, TingerMapBuffer<String, DataSource>> sources = new TingerMapBuffer<>();
    private Map<JdbcDriver, DataSourceBuilder> buildMapper = new HashMap<>();

    @Override
    public void install() {
        List<DataSourceBuilder> builders = ServiceLoaderUtils.scan(DataSourceBuilder.class);
        for (DataSourceBuilder builder : builders) {
            buildMapper.put(builder.driver(), builder);
        }
    }

    @Override
    public void destroy() {
        sources.values().forEach(x -> x.values().forEach(y -> {
            if (y instanceof Closeable) {
                IoUtils.close((Closeable) y);
            } else if (y instanceof AutoCloseable) {
                IoUtils.close((AutoCloseable) y);
            }
        }));
    }

    @Override
    public DataSource singlet(JdbcDriver driver, String sourceName) {
        String configKey = driver.name().toLowerCase() + "_" + sourceName;
        TingerMapBuffer<String, DataSource> buffer = sources.get(driver, TingerMapBuffer::new);
        return buffer.get(configKey, () -> {
            DataSourceBuilder builder = buildMapper.get(driver);
            Config config = this.application.module(ConfigModule.class).getConfig();
            DataSourceConfig sourceConfig = config.load(configKey, DataSourceConfig.class);
            return builder.build(sourceConfig);
        });
    }

    @Override
    public List<DataSource> sharding(JdbcDriver driver, String sourceName) {

        Config config = this.application.module(ConfigModule.class).getConfig();
        List<String> shardingNames = config.list(driver.name() + "_" + sourceName, String.class);
        return shardingNames.stream().map(x -> singlet(driver, x)).collect(Collectors.toList());
    }
}
