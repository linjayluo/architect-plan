package com.llj.cloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PackageName:   com.llj.cloud.annotation
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2020/2/1 15:43
 * Updater:     by luolinjie
 * Update_Date: 2020/2/1
 * Update_Description: luolinjie 补充
 **/
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//定义该注解的生命周期
public @interface ExtAnnotation {
}
