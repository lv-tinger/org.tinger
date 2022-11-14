package org.tinger.remote;

import org.tinger.core.apps.ApplicationWeaverModule;
import org.tinger.core.remote.RemoteClient;
import org.tinger.core.remote.RemoteClientModule;

/**
 * Created by tinger on 2022-10-05
 */
public class TingerRemoteClientModule extends ApplicationWeaverModule implements RemoteClientModule {
    private RemoteClient remoteClient;

    @Override
    public void install() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public RemoteClient getRemoteClient() {
        return remoteClient;
    }
}