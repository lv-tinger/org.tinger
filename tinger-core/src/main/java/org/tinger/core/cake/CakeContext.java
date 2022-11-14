package org.tinger.core.cake;

import org.tinger.common.buffer.TingerMapBuffer;

/**
 * Created by tinger on 2022-11-11
 */
public class CakeContext {
    private final TingerMapBuffer<Class<?>, CakeLoader<?>> buffer = new TingerMapBuffer<>();

    @SuppressWarnings("unchecked")
    public <T> T bake(Class<T> type) {
        CakeLoader<T> loader = (CakeLoader<T>) buffer.get(type, () -> new CakeLoader<>(type));
        return bake(loader, loader.getTacit());
    }

    @SuppressWarnings("unchecked")
    public <T> T bake(Class<T> type, String name) {
        CakeLoader<T> loader = (CakeLoader<T>) buffer.get(type, () -> new CakeLoader<>(type));
        return bake(loader, name);
    }

    private <T> T bake(CakeLoader<T> loader, String name) {
        CakeDefine<T> define = loader.get(name);
        return define.instance(new CakeOven() {
            @Override
            public <P> P bake(Class<P> type) {
                P instance = CakeOven.super.bake(type);
                return instance;
            }
        });
    }
}