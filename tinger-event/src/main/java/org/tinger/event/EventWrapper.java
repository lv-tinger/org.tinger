package org.tinger.event;

import lombok.Getter;
import lombok.Setter;
import org.tinger.core.event.Event;

/**
 * Created by tinger on 2022-10-04
 */
public class EventWrapper {
    @Getter
    @Setter
    private Event event;

    public void clean() {
        this.event = null;
    }
}
