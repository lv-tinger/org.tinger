package org.tinger.core.event;

import org.tinger.core.common.Named;

/**
 * Created by tinger on 2022-09-24
 */
public interface EventChannel extends Named {

    int getSize();

    int getBusy();

    int getIdle();

    int percent();

    void listen(Consumer consumer);

    void cancel(Consumer consumer);

    void notice(Event event);
}