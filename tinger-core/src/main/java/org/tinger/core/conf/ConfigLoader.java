package org.tinger.core.conf;

import java.util.Map;

/**
 * Created by tinger on 2022-10-04
 */
public interface ConfigLoader {
    Map<String, Object> load();
}
