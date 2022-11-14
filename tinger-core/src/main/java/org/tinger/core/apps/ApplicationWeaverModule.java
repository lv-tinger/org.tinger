package org.tinger.core.apps;

/**
 * Created by tinger on 2022-10-05
 */
public class ApplicationWeaverModule implements ApplicationWeaver {
    protected Application application;

    @Override
    public void weave(Application application) {
        this.application = application;
    }
}