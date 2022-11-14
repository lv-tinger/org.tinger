package org.tinger.core.apps;

import org.tinger.core.common.Named;
import org.tinger.core.common.Ordered;

public interface Module extends Ordered, Named {
    void install();

    void destroy();
}