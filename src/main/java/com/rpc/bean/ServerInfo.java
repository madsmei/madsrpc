package com.rpc.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 服务器信息类
 * @Author Mads
 * @Date 2020/6/30 11:08
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    private String url;
}
