package org.tinger.core.data.source;

import org.tinger.core.data.JdbcDriver;

import javax.sql.DataSource;

public interface DataSourceBuilder {
    DataSource build(DataSourceConfig config);
    JdbcDriver driver();
}
