package org.tinger.core.data.repository;

import org.tinger.core.data.queryable.Criteria;
import org.tinger.core.data.queryable.Update;
import org.tinger.core.model.Entity;

import java.util.List;

/**
 * Created by tinger on 2022-10-15
 */
public interface Repository<T, K> {
    T select(K id);

    T update(T document);

    T create(T document);

    T upsert(T document);

    T delete(T document);

    List<T> delete(Criteria criteria);

    List<T> update(Update update, Criteria criteria);

    List<T> select();

    List<T> select(Criteria criteria);
}