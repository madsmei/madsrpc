package com.rpc.config;

import com.mads.userService.IuserAPI;
import com.rpc.proxy.ProxyCreator;
import com.rpc.proxy.impl.JDKProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Description
 * @Author Mads
 * @Date 2020/6/30 11:47
 * @Version 1.0
 */
@Order(-2)
@Configuration
public class RpcConfig {

    /***
     * @Author Mads
     * @Description 创建JDK代理工具类
     *  如果日后想更换 生成代理类的方式。改动这一个方法即可
     * @Date 11:49 2020/6/30
    **/
    @Bean
    public ProxyCreator jdkProxyCreator() {
        return new JDKProxyCreator();
    }

    /*****
     * @Author Mads
     * @Description 使用spring的FactoryBean接口来生成代理对象
     * #TODO 后面改成 扫描 注解@RpcServer修饰的所有类。并按照下面方式生成代理类，这样使用者就不用在自己定义这玩意了。
     *   实现思路：考虑结合AOP实现,或者实现ApplicationContextAware和initXXX接口拿到注解所有的类统一生成代理
     * @Date 16:36 2020/6/30
     * @param proxyCreator
    **/
    @Bean
    public FactoryBean<IuserAPI> getIuserAPI(ProxyCreator proxyCreator){
        return new FactoryBean<IuserAPI>() {
            @Override
            public IuserAPI getObject() throws Exception {

                //返回需要被代理对象
                return (IuserAPI) proxyCreator.createProxy(IuserAPI.class);
            }

            @Override
            public Class<?> getObjectType() {
                return IuserAPI.class;
            }
        };
    }

//    FactoryBean<Class> baseProxy(ProxyCreator proxyCreator,Class<?> type){
//        return new FactoryBean<Class>() {
//            @Override
//            public Class getObject() throws Exception {
//                //返回需要被代理对象
//                return (Class<?>) proxyCreator.createProxy(type);
//            }
//
//            @Override
//            public Class getObjectType() {
//                return type;
//            }
//        };
//    }
}
