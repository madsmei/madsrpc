package com.mads;

import com.mads.userService.IuserAPI;
import com.rpc.config.MadsFactoryBean;
import com.rpc.proxy.ProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Mads
 * @Date 2020/6/30 19:30
 * @Version 1.0
 */
@Configuration
public class config {
    @Bean
    public IuserAPI getIuserAPI(ProxyCreator proxyCreator) throws Exception {
        return new MadsFactoryBean<IuserAPI>(proxyCreator,IuserAPI.class).getObject();
    }
}
