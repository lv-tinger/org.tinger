package org.tinger.core.cake;

/**
 * Created by tinger on 2022-11-11
 */
public interface CakeOven {
    default <T> T bake(Class<T> type) {
        try {
            return type.getConstructor().newInstance();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
