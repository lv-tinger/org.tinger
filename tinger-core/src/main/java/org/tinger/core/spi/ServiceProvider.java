package org.tinger.core.spi;

/**
 * Created by tinger on 2022-11-10
 */
public interface ServiceProvider {
    void put(Class<?> type, Object element);

    void put(Class<?> type, String name, Object element);

    <T> T get(Class<?> type);

    <T> T get(Class<?> type, String name);
}