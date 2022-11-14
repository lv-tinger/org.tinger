package org.tinger.core.data.annotation;

import java.lang.annotation.*;

/**
 * Created by tinger on 2022-10-17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface CreateTime {
}