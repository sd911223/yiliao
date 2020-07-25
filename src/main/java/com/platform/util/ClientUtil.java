package com.platform.util;

import grakn.client.GraknClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 创建连接grakn的客户端
 *
 * @author shitou
 */

@Component
public class ClientUtil {
    @Value("${grakn.server.address}")
    private String address;
    private static GraknClient client;

    //返回客户端
    public GraknClient getClient() {
        if (client == null) synchronized (this) {

            client = new GraknClient(address);
        }
        return client;

    }


}
