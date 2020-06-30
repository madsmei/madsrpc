package com.rpc.rest.handlers;

import com.rpc.bean.MethodInfo;
import com.rpc.bean.ServerInfo;
import com.rpc.rest.RestHandler;
import com.rpc.util.WebClientUtil;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Description 反应式 webClient 调用接口
 * @Author Mads
 * @Date 2020/6/30 11:54
 * @Version 1.0
 */
public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClientUtil.getWebClient(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {

        Object result = null;
        WebClient.ResponseSpec responseSpec = client.method(methodInfo.getMethodName())//请求方法
                .uri(methodInfo.getUrl())//请求uri
                .accept(MediaType.APPLICATION_JSON)//请求方式json
                .retrieve();//发出请求
        if(methodInfo.isReturnFlux()) {
            result = responseSpec.bodyToFlux(methodInfo.getReturnElementType());
        }else{
            result = responseSpec.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
