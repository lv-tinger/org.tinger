package org.tinger.core.remote;

import lombok.Data;

/**
 * Created by tinger on 2022-10-05
 */
@Data
public class RemoteResponse {
    private String id;
    private Boolean success;
    private Integer code;
    private Object returnValue;
}
