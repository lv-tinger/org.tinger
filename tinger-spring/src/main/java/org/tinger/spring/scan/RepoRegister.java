package org.tinger.spring.scan;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.tinger.core.data.annotation.Repo;

@Component
public class RepoRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RepoScanner repoScanner = new RepoScanner(registry, false);
        repoScanner.addIncludeFilter(new AnnotationTypeFilter(Repo.class));
        repoScanner.doScan("org.tinger");
    }
}