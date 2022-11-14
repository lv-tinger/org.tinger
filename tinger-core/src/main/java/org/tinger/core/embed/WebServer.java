package org.tinger.core.embed;

/**
 * Created by tinger on 2022-10-06
 */
public interface WebServer {
    void start();

    void close();

    void register(Controller controller);
}
