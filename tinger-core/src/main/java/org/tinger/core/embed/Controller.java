package org.tinger.core.embed;

/**
 * Created by tinger on 2022-10-06
 */
public interface Controller {
    String path();

    Object execute(Request request, Response response);
}
