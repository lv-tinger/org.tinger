package org.tinger.config;

import org.tinger.common.utils.ResourceUtils;
import org.tinger.common.utils.StringUtils;
import org.tinger.core.conf.ConfigLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tinger on 2022-10-04
 */
public abstract class LocalFileConfigLoader implements ConfigLoader {
    private static final String path = "classpath://config/tinger.config.properties";

    protected abstract String getPath();

    @Override
    public Map<String, Object> load() {
        List<String> strings = ResourceUtils.readLines(getPath());
        HashMap<String, Object> config = new HashMap<>();
        for (String string : strings) {
            int index = StringUtils.indexOf(string, "=");
            if (index == -1) {
                continue;
            }
            String key = StringUtils.trim(string.substring(0, index));
            String value = StringUtils.trim(string.substring(index + 1));
            if (StringUtils.isAnyEmpty(key, value)) {
                continue;
            }

            config.put(key, value);
        }
        return config;
    }
}