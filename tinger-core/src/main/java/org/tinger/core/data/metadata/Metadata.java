package org.tinger.core.data.metadata;

/**
 * Created by tinger on 2022-10-15
 */
public interface Metadata<T, ID> {
    String getMetaName();

    Class<T> getMetaType();
}