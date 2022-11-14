package org.tinger.data.metadata.builder.wrapper;

import java.lang.reflect.Field;

public interface PropertyWrapper {
    void process(Field field);
}
