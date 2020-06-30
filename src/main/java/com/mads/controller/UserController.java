package com.mads.controller;

import com.mads.userService.IuserAPI;
import com.mads.userService.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description
 * @Author Mads
 * @Date 2020/6/30 10:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IuserAPI userAPI;

    @GetMapping("/")
    public Flux<User> test() {
        return userAPI.getAll();
    }
}
