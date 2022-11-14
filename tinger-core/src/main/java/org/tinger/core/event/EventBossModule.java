package org.tinger.core.event;

import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-10-04
 */
public interface EventBossModule extends Module {
    EventBoss getEventBoss();
}
