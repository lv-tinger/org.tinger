package org.tinger.embed;

import org.tinger.common.buffer.TingerMapBuffer;
import org.tinger.core.embed.Controller;
import org.tinger.core.embed.WebServer;

/**
 * Created by tinger on 2022-10-07
 */
public abstract class TingerWebServer implements WebServer {
    protected TingerMapBuffer<String, Controller> mapper = new TingerMapBuffer<>();
    protected int port;

    public void setPort(int port) {
        this.port = port;
    }
}