package org.tinger.core.spi;

import org.tinger.common.buffer.TingerMapBuffer;

/**
 * Created by tinger on 2022-11-10
 */
public class DefaultServiceProvider implements ServiceProvider {
    private final TingerMapBuffer<Class<?>, TingerMapBuffer<String, Object>> buffer = new TingerMapBuffer<>();

    @Override
    public void put(Class<?> type, Object element) {

    }

    @Override
    public void put(Class<?> type, String name, Object element) {
    }

    @Override
    public <T> T get(Class<?> type) {
        return null;
    }

    @Override
    public <T> T get(Class<?> type, String name) {
        return null;
    }
}