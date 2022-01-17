package com.example.repeatdemo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止重复提交的注解
 *
 * @author 2019年6月18日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface JRepeat {

    /**
     * 超时时间
     *
     * @return
     */
    int lockTime();


    /**
     * redis 锁key的
     *
     * @return redis 锁key
     */
    String lockKey() default "";



}