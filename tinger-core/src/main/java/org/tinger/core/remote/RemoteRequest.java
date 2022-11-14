package org.tinger.core.remote;

import lombok.Data;

/**
 * Created by tinger on 2022-10-05
 */
@Data
public class RemoteRequest {
    private String id;
    private String serv;
    private String ver;
    private String meth;
    private String[] types;
    private Object[] values;
}
