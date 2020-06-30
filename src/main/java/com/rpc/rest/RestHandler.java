package com.rpc.rest;

import com.rpc.bean.MethodInfo;
import com.rpc.bean.ServerInfo;
import java.lang.reflect.Method;

/**
 * @Description rest请求调用。
 *  考虑以后 调用的方式会发生变化。这里使用接口
 * @Author Mads
 * @Date 2020/6/30 11:28
 * @Version 1.0
 */
public interface RestHandler {

    /*****
     * @Description 初始化 服务器信息
     * @Date 11:36 2020/6/30
     * @return
    **/
    void init(ServerInfo serverInfo);

    /****
     * @Author Mads
     * @Description 调用远程服务，返回数据
     * @Date 11:37 2020/6/30
    **/
    Object invokeRest(MethodInfo methodInfo);
}
