package org.tinger.config;

import org.tinger.common.serialize.JsonSerializer;
import org.tinger.core.conf.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TingerConfig implements Config {

    Map<String, Object> mapper = new HashMap<>();

    TingerConfig conf(String name, Object value) {
        this.mapper.put(name, value);
        return this;
    }

    TingerConfig conf(Map<String, Object> config) {
        this.mapper.putAll(config);
        return this;
    }

    @Override
    public Object load(String name) {
        return mapper.get(name);
    }

    @Override
    public <T> T load(String name, Class<T> type) {
        Object o = mapper.get(name);
        if (o == null) {
            return null;
        }
        JsonSerializer serializer = JsonSerializer.getInstance();
        String json = serializer.toJson(o);
        return serializer.fromJson(json, type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> list(String name, Class<T> type) {
        Object o = mapper.get(name);
        if (o == null) {
            return null;
        }
        JsonSerializer serializer = JsonSerializer.getInstance();
        String json = serializer.toJson(o);
        return (List<T>) serializer.fromJson(json, List.class, type);
    }
}