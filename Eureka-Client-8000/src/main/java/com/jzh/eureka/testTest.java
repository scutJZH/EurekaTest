package com.jzh.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/v1/test/discovery")
    public List<ServiceInstance> discoverInstance() {
        List<ServiceInstance> instances = discoveryClient.getInstances("client-8000");
        instances.forEach(instance -> {
            instance.getHost();
            instance.getPort();
            instance.getInstanceId();
        });
        return discoveryClient.getInstances("client-8000");
    }

    @GetMapping("/v1/test/discovery/service")
    public void discoveryService() {
        discoveryClient.getServices().forEach(name -> {
            System.out.println(name);
            List<ServiceInstance> sis = discoveryClient.getInstances(name);
            sis.forEach(System.out::println);
        });
    }

}
