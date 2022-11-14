package org.tinger.spring.module;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.tinger.core.apps.Application;
import org.tinger.core.data.DataModule;

@Component
public class DataExtend implements FactoryBean<DataModule> {
    @Override
    public DataModule getObject() {
        return Application.getInstance().module(DataModule.class);
    }

    @Override
    public Class<?> getObjectType() {
        return DataModule.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
