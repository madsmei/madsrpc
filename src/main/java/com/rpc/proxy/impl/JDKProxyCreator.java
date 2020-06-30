package com.rpc.proxy.impl;

import com.rpc.RpcServer;
import com.rpc.bean.MethodInfo;
import com.rpc.bean.ServerInfo;
import com.rpc.proxy.ProxyCreator;
import com.rpc.rest.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 使用 JDK 生成代理类
 * @Author Mads
 * @Date 2020/6/30 11:03
 * @Version 1.0
 */
@Slf4j
public class JDKProxyCreator implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> type) {

        log.info("createProxy#type{}",type);

        ServerInfo serverInfo = extractServerInfo(type);

        log.info("createProxy#serverInfo{}",serverInfo.toString());
        //给每一个代理类一个实现
        RestHandler restHandler = null;
        //初始化服务器信息（初始化webclient）
        restHandler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{type},
                (proxy, method, args)->{
                    //根据方法和参数得到调用信息
                    MethodInfo methodInfo = extractMethodInfo(method,args);

                    log.info("createProxy#methodInfo{}",methodInfo.toString());
                    //调用rest
                   return restHandler.invokeRest(method);
                });
    }

    /*****
     * @Author Mads
     * @Description 封装 rest调用的相关参数
     * @Date 13:40 2020/6/30
     * @param method
     * @param args
    **/
    private MethodInfo extractMethodInfo(Method method, Object[] args) {

        MethodInfo methodInfo = new MethodInfo();

        /*****第一步 根据方法上的注解，拿到@getMapping("/uri") 中的uri信息，**/
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof GetMapping) {
                GetMapping getMapping = (GetMapping)annotation;
                methodInfo.setUrl(getMapping.value()[0]);
                methodInfo.setMethodName(HttpMethod.GET);
            }else if(annotation instanceof PostMapping) {
                PostMapping postMapping = (PostMapping)annotation;
                methodInfo.setUrl(postMapping.value()[0]);
                methodInfo.setMethodName(HttpMethod.POST);
            }else if(annotation instanceof DeleteMapping) {
                DeleteMapping deleteMapping = (DeleteMapping)annotation;
                methodInfo.setUrl(deleteMapping.value()[0]);
                methodInfo.setMethodName(HttpMethod.DELETE);
            }else if(annotation instanceof PutMapping) {
                PutMapping putMapping = (PutMapping)annotation;
                methodInfo.setUrl(putMapping.value()[0]);
                methodInfo.setMethodName(HttpMethod.PUT);
            }
        }

        /****第二步 拼装请求参数和body***/
        //参数和值对应map
        Map<String,Object> restParameterMap = new LinkedHashMap<>();

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {

            //是否带 @PathVariable
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            if(null != pathVariable) {
                restParameterMap.put(pathVariable.value(),args[i]);
            }

            //是否带 @RequestBody
            RequestBody bodyVariable = parameters[i].getAnnotation(RequestBody.class);
            if(null != bodyVariable) {
                methodInfo.setBody(Mono.just(args[i]));
            }
        }
        methodInfo.setMethodParams(restParameterMap);

        return methodInfo;
    }

    /*****
     * @Author Mads
     * @Description 根据接口得到服务器信息
     * @Date 13:09 2020/6/30
     * @param type
    **/
    private ServerInfo extractServerInfo(Class<?> type) {
        //拿到自定义注解里的值，@RpcServer("http://api.mads.com/user/")
        RpcServer rpcServer = type.getAnnotation(RpcServer.class);

        return new ServerInfo(rpcServer.value());
    }
}
