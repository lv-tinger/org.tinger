package org.tinger.core.spring;

import org.springframework.context.ApplicationContext;
import org.tinger.core.apps.Module;

/**
 * Created by tinger on 2022-10-04
 */
public interface SpringModule extends Module {
    ApplicationContext getApplicationContext();
}