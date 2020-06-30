package com.rpc.proxy;

/**
 * @Description 创建代理类的接口
 *   这里考虑以后使用创建代理的方法可能会变动。所以使用了接口
 * @Author Mads
 * @Date 2020/6/30 10:59
 * @Version 1.0
 */
public interface ProxyCreator {
    /****
     * @Description 创建代理类
     * @Date 11:00 2020/6/30
    **/
    Object createProxy(Class<?> type);
}
