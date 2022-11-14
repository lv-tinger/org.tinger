package org.tinger.core.embed;

import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-10-06
 */
public interface WebModule extends Module {
    WebServer getWebServer();
}