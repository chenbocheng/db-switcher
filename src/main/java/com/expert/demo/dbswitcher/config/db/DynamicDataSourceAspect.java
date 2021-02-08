package com.expert.demo.dbswitcher.config.db;


import com.expert.demo.dbswitcher.config.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Pointcut("@annotation(com.expert.demo.dbswitcher.config.annotation.DS)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String dsKey = getDSAnnotation(joinPoint).value();
        DataSourceKeyHolder.setKey(dsKey);
        log.debug("DataSource PointCut: Switch to {}", dsKey);
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceKeyHolder.clear();
            log.debug("DataSource PointCut: {} closed", dsKey);
        }
    }

    private DS getDSAnnotation(ProceedingJoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        DS dsAnnotation = targetClass.getAnnotation(DS.class);
        // 先判断类的注解，再判断方法注解
        if (Objects.nonNull(dsAnnotation)) {
            return dsAnnotation;
        } else {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            return methodSignature.getMethod().getAnnotation(DS.class);
        }
    }
}
