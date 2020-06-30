package com.mads.userService;

import com.rpc.RpcServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description 操作用户信息的,和需要调用的接口定义一致
 * @Author Mads
 * @Date 2020/6/30 10:08
 * @Version 1.0
 */
@RpcServer("http://localhost:8080/proxy")
public interface IuserAPI {

    //这里可以使用自己的注解。也可以使用Spring的。。。
    @GetMapping("/")
    Flux<User> getAll();

    @GetMapping("/{id}")
    Mono<User> getById(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);

    @PostMapping("/free")
    Mono<String> testRest();
}
