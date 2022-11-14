package org.tinger.data.metadata;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;

@Getter
@Setter
public class Metadata<T, ID> {
    private String name;
    private Class<T> type;
    private Constructor<T> constructor;
}