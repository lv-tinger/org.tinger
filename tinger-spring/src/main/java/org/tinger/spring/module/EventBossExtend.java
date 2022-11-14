package org.tinger.spring.module;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.tinger.core.apps.Application;
import org.tinger.core.event.EventBoss;
import org.tinger.core.event.EventBossModule;
import org.tinger.core.event.EventChannel;

/**
 * Created by tinger on 2022-10-04
 */
@Component
public class EventBossExtend implements FactoryBean<EventBoss> {

    @Override
    public EventBoss getObject() {
        return Application.getInstance().module(EventBossModule.class).getEventBoss();
    }

    @Override
    public Class<?> getObjectType() {
        return EventChannel.class;
    }
}
