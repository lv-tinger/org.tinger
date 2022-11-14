package org.tinger.mysql.builder;

import org.tinger.common.utils.FieldUtils;
import org.tinger.common.utils.ReflectUtils;
import org.tinger.core.data.annotation.*;
import org.tinger.mysql.handler.JdbcHandler;
import org.tinger.mysql.handler.JdbcHandlerHolder;
import org.tinger.mysql.metadata.JdbcMetadata;
import org.tinger.mysql.metadata.JdbcProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tinger on 2022-10-18
 */
public class MetadataBuilder {
    public static <T, K> JdbcMetadata<T, K> build(Class<T> type) {
        Field[] fields = ReflectUtils.getMemberFields(type);
        List<JdbcProperty> jdbcProperties = Arrays.stream(fields).map(MetadataBuilder::build).collect(Collectors.toList());

        JdbcMetadata<T, K> metadata = new JdbcMetadata<>();
        metadata.setType(type);
        metadata.setConstructor(ReflectUtils.getConstructor(type));
        metadata.setProperties(jdbcProperties);
        return metadata;
    }

    private static JdbcProperty build(Field field) {
        Annotation ignore = FieldUtils.getAnnotation(field, Ignore.class);
        if (ignore != null) {
            return null;
        }

        field.setAccessible(true);

        JdbcProperty property = new JdbcProperty();
        property.setType(field.getType());
        property.setField(field);
        property.setName(field.getName());
        JdbcHandler<?> jdbcHandler = JdbcHandlerHolder.getInstance().get(field.getType());
        property.setHandler(jdbcHandler);

        column(property, field);

        attr(property, field, PrimaryKey.class);
        attr(property, field, CreateTime.class);
        attr(property, field, UpdateTime.class);
        attr(property, field, Version.class);
        attr(property, field, Status.class);

        return property;
    }

    private static void column(JdbcProperty property, Field field) {
        String name = field.getName();

        Column column = FieldUtils.getAnnotation(field, Column.class);

        if (column != null) {
            name = column.name();
        }

        property.setColumn(name);
    }

    private static void attr(JdbcProperty property, Field field, Class<? extends Annotation> anno) {
        Annotation annotation = FieldUtils.getAnnotation(field, anno);
        if (annotation == null) {
            return;
        }

        if (PrimaryKey.class.equals(anno)) {
            property.setAttr(JdbcProperty.PRIMARY_KEY);
        } else if (CreateTime.class.equals(anno)) {
            property.setAttr(JdbcProperty.CREATE_TIME);
        } else if (UpdateTime.class.equals(anno)) {
            property.setAttr(JdbcProperty.UPDATE_TIME);
        } else if (Version.class.equals(anno)) {
            property.setAttr(JdbcProperty.VERSION);
        } else if (Status.class.equals(anno)) {
            property.setAttr(JdbcProperty.STATUS);
        }
    }
}