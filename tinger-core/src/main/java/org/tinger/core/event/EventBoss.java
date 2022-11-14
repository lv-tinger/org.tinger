package org.tinger.core.event;

/**
 * Created by tinger on 2022-10-04
 */
public interface EventBoss {
    EventChannel getChannel(String name);
}
