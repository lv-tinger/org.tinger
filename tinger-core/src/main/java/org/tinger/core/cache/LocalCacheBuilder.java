package org.tinger.core.cache;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by tinger on 2022-10-15
 */
public interface LocalCacheBuilder<T> {
    LocalCacheBuilder<T> refresh(Supplier<List<T>> supplier, long time);

    LocalCacheBuilder<T> initial(Supplier<List<T>> supplier);

    LocalCache<T> build();
}