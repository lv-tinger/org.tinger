package org.tinger.core.data.source;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSourceConfig {
    private String url;
    private String username;
    private String password;
    private Integer minIdle = 1;
    private Integer maxSize = 1;
    private Boolean autoCommit = true;
    private Integer idleTimeout = 30000;
    private String testQuery = "SELECT 1";
}
