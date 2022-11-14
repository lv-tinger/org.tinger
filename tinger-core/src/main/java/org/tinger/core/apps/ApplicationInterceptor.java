package org.tinger.core.apps;

import org.tinger.core.common.Named;
import org.tinger.core.common.Ordered;

/**
 * Created by tinger on 2022-10-04
 */
public interface ApplicationInterceptor extends Named, Ordered {
    void preHandler(Application application);

    void sufHandler(Application application);

    ApplicationEventType getEventType();
}