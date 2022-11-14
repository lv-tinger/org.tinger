package org.tinger.core.event;

/**
 * Created by tinger on 2022-09-24
 */
public interface Consumer {
    boolean accept(Event event);

    void process(Event event);
}