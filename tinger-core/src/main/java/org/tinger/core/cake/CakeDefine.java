package org.tinger.core.cake;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by tinger on 2022-11-11
 */
public class CakeDefine<T> {
    private Class<T> type;

    public CakeDefine(Class<T> type) {
        this.type = type;
    }

    private T instance;

    public T instance(CakeOven oven) {
        if (instance != null) {
            return instance;
        }
        synchronized (this) {
            if (instance != null) {
                return instance;
            }
            instance = oven.bake(type);
            return instance;
        }
    }
}
