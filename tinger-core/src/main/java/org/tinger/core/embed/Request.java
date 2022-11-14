package org.tinger.core.embed;

/**
 * Created by tinger on 2022-10-14
 */
public interface Request {
    String string(String parameterName);

    int integer(String parameterName);

    <T> T body(Class<T> type);

    String text();
}
