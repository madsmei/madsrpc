package com.rpc.util;

import io.netty.channel.ChannelOption;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.Map;

/**
 * @Description
 * @Author Mads
 * @Date 2020/6/17 11:05
 * @Version 1.0
 */
public class WebClientUtil {

    private WebClientUtil(){}

    private static TcpClient getTcpClient() {
        return TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000);
    }

    public static WebClient getWebClient(String url) {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(getTcpClient())))
                .defaultHeader("Content-Type", "application/json")
                .baseUrl(url)
                .build();
    }


    public static <K> Mono<K> post(String url,String uri,Mono requestMono,Class<K> result) {
        WebClient.RequestBodyUriSpec post = getWebClient(url)
                .post();//请求类型

        if(null != requestMono) {
            post.body(requestMono,requestMono.getClass());//请求消息体
        }
        Mono<K> resultMono = post.uri(uri)//请求地址和参数
                .retrieve()//获取响应主体并对其进行解码的最简单方法
                .bodyToMono(result);
        return resultMono;//返回结果
    }

    public static void main(String[] args) {

        Map<String,String> requestMap = new HashedMap();
        requestMap.put("token","8c6a16396bcbe2aed2fe0c12f0af65f4");
        Mono<Map> requestMono = Mono.just(requestMap);

        Mono map = WebClientUtil.post("http://testv2.holla.world", "/api/Conversation/getList?id=123", requestMono, Map.class);

        System.out.println(111);
    }
}
