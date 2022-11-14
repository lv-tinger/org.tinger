package org.tinger.core.data;

import org.tinger.core.apps.Module;

import javax.sql.DataSource;
import java.util.List;

public interface DataModule extends Module {
    DataSource singlet(JdbcDriver driver, String sourceName);
    List<DataSource> sharding(JdbcDriver driver, String sourceName);
}