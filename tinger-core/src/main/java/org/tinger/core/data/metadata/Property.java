package org.tinger.core.data.metadata;

/**
 * Created by tinger on 2022-10-15
 */
public interface Property {
    String getName();

    String getColumn();

    Class<?> getType();

    Getter getGetter();

    Setter getSetter();
}