package com.mads.userService;

import lombok.Data;
import lombok.Setter;

/**
 * @Description 接收对象，类名可以随意。字段统一
 * @Author Mads
 * @Date 2020/6/30 10:16
 * @Version 1.0
 */
@Setter
@Data
public class User {
    private String name;

    private int age;
}
