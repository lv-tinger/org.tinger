package org.tinger.event;

import org.tinger.core.apps.Application;
import org.tinger.core.apps.ApplicationWeaver;
import org.tinger.core.conf.ConfigModule;
import org.tinger.core.event.EventBoss;
import org.tinger.core.event.EventBossModule;
import org.tinger.core.event.EventChannel;

import java.util.Collection;
import java.util.List;

/**
 * Created by tinger on 2022-10-04
 */
public class TingerEventBossModule implements EventBossModule, ApplicationWeaver {
    private EventBoss eventBoss;

    private Application application;

    @Override
    public void install() {
        this.eventBoss = new TingerEventBoss();
        List<DisruptorConfig> configs = application.module(ConfigModule.class).getConfig().list("event_boss", DisruptorConfig.class);
        for (DisruptorConfig config : configs) {
            TingerEventChannel channel = new TingerEventChannel(config.getName(), config.getSize()).init().start();
            ((TingerEventBoss) eventBoss).setChannel(channel);
        }
    }

    @Override
    public void destroy() {
        Collection<EventChannel> channels = ((TingerEventBoss) eventBoss).channels();
        channels.forEach(x -> ((TingerEventChannel) x).close());
    }

    @Override
    public String getName() {
        return "TINGER-EVENT-BOSS";
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void weave(Application application) {
        this.application = application;
    }

    @Override
    public EventBoss getEventBoss() {
        return this.eventBoss;
    }
}
