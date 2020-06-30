package com.rpc.config;

import com.rpc.proxy.ProxyCreator;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description
 * @Author Mads
 * @Date 2020/6/30 18:49
 * @Version 1.0
 */
public class MadsFactoryBean<T> implements FactoryBean<T> {

    private Class<?> serviceClass;
    //代理 生成类
    private ProxyCreator proxyCreator;

    public MadsFactoryBean (ProxyCreator proxyCreator,Class<?> serviceClass) {
        this.proxyCreator = proxyCreator;
        this.serviceClass = serviceClass;
    }

    @Override
    public T getObject() throws Exception {
        //返回需要被代理对象
        return (T) proxyCreator.createProxy(serviceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceClass;
    }
}
