package org.tinger.event;

import org.tinger.core.event.EventBoss;
import org.tinger.core.event.EventChannel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tinger on 2022-10-04
 */
public class TingerEventBoss implements EventBoss {
    private Map<String, EventChannel> mapper = new HashMap<>();

    @Override
    public EventChannel getChannel(String name) {
        return mapper.get(name);
    }

    public void setChannel(EventChannel channel) {
        mapper.put(channel.getName(), channel);
    }

    public Collection<EventChannel> channels() {
        return this.mapper.values();
    }
}
