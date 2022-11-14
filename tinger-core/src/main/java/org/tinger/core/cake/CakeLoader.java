package org.tinger.core.cake;

import lombok.Getter;
import org.tinger.common.utils.ClassUtils;
import org.tinger.common.utils.CollectionUtils;
import org.tinger.common.utils.ResourceUtils;
import org.tinger.common.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tinger on 2022-11-11
 */
public class CakeLoader<T> {
    @Getter
    private Class<T> type;
    @Getter
    private String tacit;
    private Map<String, CakeDefine<T>> defines = new HashMap<>();

    public CakeLoader(Class<T> type) {
        this.type = type;
        SPI spi = type.getDeclaredAnnotation(SPI.class);
        if (spi == null) {
            throw new RuntimeException();
        }
        this.tacit = spi.value();

        List<String> strings = ResourceUtils.readLines("classpath://tinger/" + type.getName());
        if (CollectionUtils.isEmpty(strings)) {
            throw new RuntimeException();
        }

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        for (String string : strings) {
            if (StringUtils.isEmpty(string) || string.startsWith("#")) {
                continue;
            }
            int index = string.indexOf("=");
            String name = string.substring(0, index).trim();
            String impl = string.substring(index + 1).trim();
            try {
                Class<?> clazz = classLoader.loadClass(impl);
                if (!clazz.isAssignableFrom(type)) {
                    throw new RuntimeException();
                }
                CakeDefine<T> define = (CakeDefine<T>) new CakeDefine<>(clazz);
                defines.put(name, define);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException();
            }
        }
    }

    public CakeDefine<T> get() {
        return this.get(this.tacit);
    }

    public CakeDefine<T> get(String name) {
        return this.defines.get(name);
    }
}
