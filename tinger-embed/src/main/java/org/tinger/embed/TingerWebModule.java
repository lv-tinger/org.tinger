package org.tinger.embed;

import org.tinger.common.utils.ConverterUtil;
import org.tinger.common.utils.ServiceLoaderUtils;
import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.conf.Config;
import org.tinger.core.conf.ConfigModule;
import org.tinger.core.embed.WebModule;
import org.tinger.core.embed.WebServer;
import org.tinger.core.listen.Listener;

/**
 * Created by tinger on 2022-10-07
 */
public class TingerWebModule extends ApplicationWeaverModule implements WebModule {
    private WebServer server;

    @Override
    public void install() {
        Config config = this.application.module(ConfigModule.class).getConfig();
        this.server = ServiceLoaderUtils.load(WebServer.class);
        assert this.server != null;
        TingerWebServer tingerWebServer = (TingerWebServer) server;
        int port = ConverterUtil.toInteger(config.load("http_port"));
        tingerWebServer.setPort(port);
        this.application.consume(new Listener() {
            @Override
            public String getChannel() {
                return "APPLICATION-STARTED";
            }

            @Override
            public void process(Object object) {
                server.start();
            }
        });
    }

    @Override
    public void destroy() {
        this.server.close();
    }

    @Override
    public WebServer getWebServer() {
        return this.server;
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public String getName() {
        return "TINGER-WEB";
    }
}