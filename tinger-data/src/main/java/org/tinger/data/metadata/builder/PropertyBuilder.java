package org.tinger.data.metadata.builder;

import org.tinger.data.metadata.Property;

import java.lang.reflect.Field;

public interface PropertyBuilder {
    Property build(Field field);
}