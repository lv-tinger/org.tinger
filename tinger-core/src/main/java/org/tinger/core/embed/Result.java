package org.tinger.core.embed;

import lombok.Builder;
import lombok.Data;

/**
 * Created by tinger on 2022-10-14
 */
@Data
@Builder
public class Result {
    private int code;
    private boolean success;
    private String message;
    private Object content;
}
