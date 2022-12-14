package org.tinger.bootstrap;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.tinger.bootstrap.module.ModuleScanner;
import org.tinger.common.utils.ArrayUtils;
import org.tinger.common.utils.ConverterUtil;
import org.tinger.common.utils.ServiceLoaderUtils;
import org.tinger.common.utils.StringUtils;
import org.tinger.core.apps.*;
import org.tinger.core.conf.ConfigModule;
import org.tinger.core.listen.Listener;
import org.tinger.core.listen.Publisher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tinger on 2022-10-03
 */
@Slf4j
public class TingerApplication extends Application {
    public String[] args;

    @Getter
    public String name;

    private volatile String appId;

    private volatile String appSecret;

    private final Publisher publisher = new Publisher();

    private List<Module> modules = new ArrayList<>();

    private final List<ApplicationInterceptor> interceptors;

    public TingerApplication(String name, String... args) {
        this.name = name;
        this.args = args == null ? ArrayUtils.EMPTY_STRING_ARRAY : args;
        this.interceptors = ServiceLoaderUtils.scan(ApplicationInterceptor.class);
    }

    @Override
    public String getAppId() {
        if (StringUtils.isNotEmpty(this.appId)) {
            return this.appId;
        }

        synchronized (this) {
            if (StringUtils.isNotEmpty(this.appId)) {
                return this.appId;
            }
            ConfigModule module = module(ConfigModule.class);
            if (module == null) {
                throw new RuntimeException();
            }
            Object value = module.getConfig().load("appId");
            if (value == null) {
                throw new RuntimeException();
            }
            return this.appId = ConverterUtil.toString(value);
        }
    }

    @Override
    public String getAppSecret() {
        if (StringUtils.isNotEmpty(this.appSecret)) {
            return this.appSecret;
        }

        synchronized (this) {
            if (StringUtils.isNotEmpty(this.appSecret)) {
                return this.appSecret;
            }
            ConfigModule module = module(ConfigModule.class);
            if (module == null) {
                throw new RuntimeException();
            }
            Object value = module.getConfig().load("appSecret");
            if (value == null) {
                throw new RuntimeException();
            }
            return this.appSecret = ConverterUtil.toString(value);
        }
    }

    @Override
    public void init() {
        setInstance(this);
        ModuleScanner scanner = ServiceLoaderUtils.load(ModuleScanner.class);
        assert scanner != null;
        this.modules = scanner.scan().stream().sorted(Comparator.comparingInt(Module::getOrder)).collect(Collectors.toList());
        this.modules.stream().filter(x -> x instanceof ApplicationWeaver).map(x -> (ApplicationWeaver) x).forEach(x -> x.weave(this));
    }

    @Override
    public void start() {
        log.info("application " + this.name + " starting");

        List<ApplicationInterceptor> interceptors = this.interceptors.stream()
                .filter(x -> x.getEventType() == ApplicationEventType.START)
                .sorted(Comparator.comparingInt(ApplicationInterceptor::getOrder))
                .collect(Collectors.toList());

        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.preHandler(this);
        }

        for (Module module : modules) {
            module.install();
            log.info(module.getName() + " installed");
        }

        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.sufHandler(this);
        }

        this.publisher.publisher("APPLICATION-STARTED", null);
    }

    @Override
    public void close() {
        List<ApplicationInterceptor> interceptors = this.interceptors.stream()
                .filter(x -> x.getEventType() == ApplicationEventType.CLOSE)
                .sorted(Comparator.comparingInt(ApplicationInterceptor::getOrder))
                .collect(Collectors.toList());

        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.preHandler(this);
        }

        List<Module> reverse = this.modules.stream().sorted((o1, o2) -> Integer.compare(o2.getOrder(), o1.getOrder())).collect(Collectors.toList());
        for (Module module : reverse) {
            module.destroy();
            log.info(module.getName() + " destroyed");
        }

        for (ApplicationInterceptor interceptor : interceptors) {
            interceptor.sufHandler(this);
        }
    }

    @Override
    public void consume(Listener listener) {
        this.publisher.subscriber(listener);
    }

    @Override
    public void produce(String channel, Object object) {
        this.publisher.publisher(channel, object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Module> T module(Class<T> type) {
        List<T> filters = modules.stream().filter(type::isInstance).map(x -> (T) x).collect(Collectors.toList());
        if (filters.size() != 1) {
            throw new RuntimeException();
        }

        return filters.get(0);
    }
}
