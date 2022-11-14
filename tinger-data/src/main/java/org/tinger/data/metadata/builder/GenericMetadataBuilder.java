package org.tinger.data.metadata.builder;

import org.tinger.common.utils.ReflectUtils;
import org.tinger.data.metadata.Metadata;
import org.tinger.data.metadata.Property;
import org.tinger.data.metadata.builder.wrapper.MetadataWrapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericMetadataBuilder implements MetadataBuilder {
    protected List<MetadataWrapper> wrappers = new LinkedList<>();

    protected PropertyBuilder propertyBuilder;

    @Override
    public Metadata<?, ?> build(Class<?> type) {
        Metadata<?, ?> metadata = instance();
        wrappers.forEach(x -> x.process(metadata, type));
        return metadata;
    }

    public List<Property> properties(Class<?> type) {
        Field[] fields = ReflectUtils.getMemberFields(type);
        return Arrays.stream(fields).map(x -> propertyBuilder.build(x)).collect(Collectors.toList());
    }

    protected abstract Metadata<?, ?> instance();
}