package org.tinger.remote;

import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.remote.RemoteServer;
import org.tinger.core.remote.RemoteServerModule;

/**
 * Created by tinger on 2022-10-05
 */
public class TingerRemoteServerModule extends ApplicationWeaverModule implements RemoteServerModule {

    private RemoteServer remoteServer;

    @Override
    public void install() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public RemoteServer getRemoteServer() {
        return remoteServer;
    }
}
