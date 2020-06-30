package com.rpc.rest.impl;

import com.rpc.bean.ServerInfo;
import com.rpc.rest.RestHandler;

import java.lang.reflect.Method;

/**
 * @ClassName WebClientRestHandler
 * @Description TODO
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
