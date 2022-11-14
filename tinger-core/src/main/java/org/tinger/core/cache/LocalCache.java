package org.tinger.core.cache;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tinger on 2022-10-15
 */
public interface LocalCache<T> {
    List<T> get(Predicate<T> predicate);
}
