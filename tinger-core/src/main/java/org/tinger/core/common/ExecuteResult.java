package org.tinger.core.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExecuteResult {
    private int code;
    private String message;
    private Object content;
}
