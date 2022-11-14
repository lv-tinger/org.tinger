package org.tinger.embed.jetty;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinger.core.embed.Controller;
import org.tinger.embed.TingerDispatcher;
import org.tinger.embed.TingerWebServer;
import org.tinger.embed.WelcomeController;

/**
 * Created by tinger on 2022-10-07
 */
@Slf4j
public class TingerJettyServer extends TingerWebServer {
    private Server server;
    private final TingerDispatcher dispatcher = new TingerDispatcher();

    @Override
    public void start() {
        dispatcher.register(new WelcomeController());
        server = new Server(port);

        /*ServerConnector connector = new SelectChannelConnector(server);
        connector.setPort(port);
        connector.setHost("localhost");
        server.addConnector(connector);*/

        ServletHandler handler = new ServletHandler();
        ServletHolder holder = new ServletHolder();
        holder.setServlet(dispatcher);
        handler.addServletWithMapping(holder, "/*");

        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void register(Controller controller) {
        dispatcher.register(controller);
    }
}
