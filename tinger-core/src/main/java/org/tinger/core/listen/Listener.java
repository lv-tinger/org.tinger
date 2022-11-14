package org.tinger.core.listen;

/**
 * Created by tinger on 2022-10-09
 */
public interface Listener {
    String getChannel();

    void process(Object object);
}
