package com.huawei.dynamicdb.dynamicdbconfig;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 * 将该注解放于需要使用的类或者方法头
 * 注意：请勿将该注解放于Mapper接口
 * @author Marke
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
