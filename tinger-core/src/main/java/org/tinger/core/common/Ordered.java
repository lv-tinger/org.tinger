package org.tinger.core.common;

public interface Ordered {
    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
