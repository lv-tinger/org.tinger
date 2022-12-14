package org.tinger.mysql.metadata;

import lombok.Data;
import org.tinger.common.utils.StringUtils;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by tinger on 2022-10-18
 */
@Data
public class JdbcMetadata<T, K> {
    private Class<?> type;
    private Constructor<T> constructor;
    private List<JdbcProperty> properties;

    public JdbcProperty getPropertyByName(String name) {
        return this.properties.stream().filter(x -> StringUtils.equals(x.getName(), name)).findFirst().orElse(null);
    }

    public JdbcProperty getPrimaryProperty() {
        return this.properties.stream().filter(x -> x.getAttr(JdbcProperty.PRIMARY_KEY)).findFirst().orElse(null);
    }
}
