package org.tinger.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tinger.core.apps.Application;
import org.tinger.core.apps.ApplicationWeaver;
import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.conf.ConfigModule;
import org.tinger.core.spring.SpringModule;

import java.util.List;

/**
 * Created by tinger on 2022-10-03
 */
public class ClassPathSpringModule extends ApplicationWeaverModule implements SpringModule {
    private ClassPathXmlApplicationContext applicationContext;

    @Override
    public void install() {
        List<String> configs = application.module(ConfigModule.class).getConfig().list("spring_configs", String.class);
        this.applicationContext = new ClassPathXmlApplicationContext(configs.toArray(new String[0]));
        this.applicationContext.start();
    }

    @Override
    public void destroy() {
        this.applicationContext.close();
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    @Override
    public String getName() {
        return "TINGER-SPRING";
    }
}