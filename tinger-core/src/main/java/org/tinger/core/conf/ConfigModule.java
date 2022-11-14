package org.tinger.core.conf;

import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-10-03
 */
public interface ConfigModule extends Module {
    Config getConfig();
}
