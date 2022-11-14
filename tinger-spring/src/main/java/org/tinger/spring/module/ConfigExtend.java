package org.tinger.spring.module;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.tinger.core.apps.Application;
import org.tinger.core.conf.Config;
import org.tinger.core.conf.ConfigModule;

/**
 * Created by tinger on 2022-10-04
 */
@Component
public class ConfigExtend implements FactoryBean<Config> {
    @Override
    public Config getObject() {
        return Application.getInstance().module(ConfigModule.class).getConfig();
    }

    @Override
    public Class<?> getObjectType() {
        return Config.class;
    }
}