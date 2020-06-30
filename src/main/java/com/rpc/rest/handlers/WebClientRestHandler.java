package com.rpc.rest.handlers;

import com.rpc.bean.ServerInfo;
import com.rpc.rest.RestHandler;

import java.lang.reflect.Method;

/**
 * @Description 反应式 webClient 调用接口
 * @Author Mads
 * @Date 2020/6/30 11:54
 * @Version 1.0
 */
public class WebClientRestHandler implements RestHandler {

    @Override
    public void init(ServerInfo serverInfo) {

    }

    @Override
    public Object invokeRest(Method method) {
        return null;
    }
}
