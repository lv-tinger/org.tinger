package org.tinger.config;

import lombok.extern.slf4j.Slf4j;
import org.tinger.common.serialize.JsonSerializer;
import org.tinger.common.utils.ResourceUtils;
import org.tinger.core.conf.ConfigLoader;

import java.util.Map;

/**
 * Created by tinger on 2022-10-04
 */
@Slf4j
public class LocalJsonConfigLoader implements ConfigLoader {
    private static final String path = "classpath://config/tinger.config.json";

    @Override
    public Map<String, Object> load() {
        String text = ResourceUtils.readText(path);
        log.debug("\n" + text);
        return JsonSerializer.getInstance().fromJson(text);
    }
}
