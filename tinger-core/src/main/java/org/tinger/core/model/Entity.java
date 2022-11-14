package org.tinger.core.model;

import org.tinger.core.data.annotation.PrimaryKey;

/**
 * Created by tinger on 2022-10-17
 */
public class Entity<K> {
    @PrimaryKey
    private K id;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }
}
