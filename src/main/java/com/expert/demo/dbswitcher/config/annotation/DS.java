package com.expert.demo.dbswitcher.config.annotation;

import com.expert.demo.dbswitcher.config.db.DataSourceConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    String value() default DataSourceConstants.DS_KEY_PRIMARY;
}
