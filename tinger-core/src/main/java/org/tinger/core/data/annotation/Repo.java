package org.tinger.core.data.annotation;

import java.lang.annotation.*;

/**
 * Created by tinger on 2022-10-21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Repo {
}
