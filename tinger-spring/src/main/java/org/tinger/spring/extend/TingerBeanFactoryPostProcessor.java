package org.tinger.spring.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 此接口在容器启动后，并且BeanDefinition已经注册到容器中以后，
 * 调用其回调函数，
 * 作用就是能拿到ConfigurableListableBeanFactory，
 * 然后操作里面的容器里面的BeanDefinition
 */
@Component
public class TingerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}