package org.tinger.core.embed;

/**
 * Created by tinger on 2022-10-14
 */
public interface Response {
    void responseBody(String text);

    void responseBody(Object object);
}
