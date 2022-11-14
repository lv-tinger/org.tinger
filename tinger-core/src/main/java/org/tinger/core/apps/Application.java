package org.tinger.core.apps;

import org.tinger.core.listen.Listener;

public abstract class Application {
    public abstract String getAppId();

    public abstract String getAppSecret();

    public abstract void init();

    public abstract void start();

    public abstract void close();

    public abstract void consume(Listener listener);

    public abstract void produce(String channel, Object object);

    public abstract <T extends Module> T module(Class<T> type);

    private static Application instance;

    protected static void setInstance(Application application) {
        instance = application;
    }

    public static Application getInstance() {
        return instance;
    }
}