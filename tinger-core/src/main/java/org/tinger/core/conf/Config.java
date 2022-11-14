package org.tinger.core.conf;

import java.util.List;

public interface Config {
    Object load(String name);

    <T> T load(String name, Class<T> type);

    <T> List<T> list(String name, Class<T> type);
}