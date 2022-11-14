package org.tinger.config;

import lombok.extern.slf4j.Slf4j;
import org.tinger.common.utils.ResourceUtils;

import java.util.Map;

/**
 * Created by tinger on 2022-10-04
 */
@Slf4j
public class LocalPropConfigLoader extends LocalFileConfigLoader {
    private static final String path = "classpath://config/tinger.config.properties";

    @Override
    protected String getPath() {
        return path;
    }

    @Override
    public Map<String, Object> load() {
        log.debug("\n" + ResourceUtils.readText(getPath()));
        return super.load();
    }
}