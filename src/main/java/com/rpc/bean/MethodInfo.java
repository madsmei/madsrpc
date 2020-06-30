package com.rpc.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Description 方法调用信息类
 * @Author Mads
 * @Date 2020/6/30 11:22
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {
    //方法上的 @getMapping("/url")
    private String url;
    //调用的方法名
    private HttpMethod methodName;
    //请求参数 （url中拼的）
    private Map<String,Object> methodParams;
    //请求消息体
    private Mono<?> body;
}
