package org.tinger.core.remote;

import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-10-05
 */
public interface RemoteServerModule extends Module {
    RemoteServer getRemoteServer();
}