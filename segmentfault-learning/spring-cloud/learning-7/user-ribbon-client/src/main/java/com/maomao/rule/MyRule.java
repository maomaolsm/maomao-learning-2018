package com.maomao.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义 {@link IRule} 实现，永远选择
 * Created by maomao on 2018/12/4.
 */
public class MyRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        ILoadBalancer loadBalancer = getLoadBalancer();

        // 获取所有可达服务器列表
        List<Server> servers = loadBalancer.getReachableServers();
        if (servers.isEmpty()) {
            return null;
        }

        // 永远选择最后一台服务器
        Server targetServer = servers.get(servers.size() - 1);
        return targetServer;
    }
}
