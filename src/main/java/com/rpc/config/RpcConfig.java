package com.rpc.config;

import com.mads.userService.IuserAPI;
import com.rpc.RpcServer;
import com.rpc.proxy.ProxyCreator;
import com.rpc.proxy.impl.JDKProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Description
 * @Author Mads
 * @Date 2020/6/30 11:47
 * @Version 1.0
 */
@Order(0)
@Configuration
public class RpcConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    
    @Autowired
    private ProxyCreator proxyCreator;

    /***
     * @Author Mads
     * @Description 创建JDK代理工具类
     *  如果日后想更换 生成代理类的方式。改动这一个方法即可
     * @Date 11:49 2020/6/30
    **/
    @Bean
    public ProxyCreator getProxyCreator() {
        return new JDKProxyCreator();
    }


    @PostConstruct
    public void inintCreateServiceProxy() throws Exception {
        Class<? extends Object> clazz = null;
        //拿到 所有的类
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcServer.class);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            clazz = entry.getValue().getClass();//获取到实例对象的class信息

            Object object = new MadsFactoryBean<>(proxyCreator, clazz).getObject();


        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
