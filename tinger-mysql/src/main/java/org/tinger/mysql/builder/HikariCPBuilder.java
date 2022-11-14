package org.tinger.mysql.builder;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.tinger.core.data.JdbcDriver;
import org.tinger.core.data.source.DataSourceBuilder;
import org.tinger.core.data.source.DataSourceConfig;

import javax.sql.DataSource;

public class HikariCPBuilder implements DataSourceBuilder {
    @Override
    public DataSource build(DataSourceConfig config) {
        HikariConfig hikariConfig = resolve(config);
        return new HikariDataSource(hikariConfig);
    }

    private HikariConfig resolve(DataSourceConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setMinimumIdle(config.getMinIdle());
        hikariConfig.setMaximumPoolSize(config.getMaxSize());
        hikariConfig.setAutoCommit(config.getAutoCommit());
        hikariConfig.setConnectionTestQuery(config.getTestQuery());
        return hikariConfig;
    }

    @Override
    public JdbcDriver driver() {
        return JdbcDriver.MYSQL;
    }
}
