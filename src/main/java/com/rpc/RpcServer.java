package com.rpc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*****
 * @Author Mads
 * @Description 服务器相关注解
 * @Date 10:19 2020/6/30
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcServer {
    String value() default "";
}
