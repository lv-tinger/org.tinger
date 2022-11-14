package org.tinger.core.listen;

import org.tinger.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinger on 2022-10-09
 */
public class Publisher {
    private List<Listener> listeners = new ArrayList<>();

    public void subscriber(Listener listener) {
        if (listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    public void publisher(String channel, Object object) {
        for (Listener listener : listeners) {
            if (StringUtils.equals(listener.getChannel(), channel)) {
                listener.process(object);
            }
        }
    }
}