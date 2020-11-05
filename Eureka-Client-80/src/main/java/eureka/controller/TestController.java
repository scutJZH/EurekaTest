package eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static final String SERVER_NAME = "CLIENT-SERVER";

    @RequestMapping("/v1/test/server_name")
    public String getApplicationName() {
        ServiceInstance serviceInstance = randomLoadBalance(SERVER_NAME);
        if (serviceInstance != null) {
            String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/v1/test";
            System.out.println(url);
            return restTemplate.getForObject(url, String.class);
        }
        return null;
    }

    private ServiceInstance randomLoadBalance(String serverName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        if (CollectionUtils.isEmpty(serviceInstances)) {
            return null;
        }
        return serviceInstances.get(new Random().nextInt(serviceInstances.size()));
    }

}
