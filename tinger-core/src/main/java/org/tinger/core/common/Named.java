package org.tinger.core.common;

public interface Named {
    String DEFAULT = "TINGER";

    default String getName() {
        return DEFAULT;
    }
}
