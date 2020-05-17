package com.trl.microservicea.appInfo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppInfoServiceImpl implements AppInfoService{

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    public AppInfoServiceImpl(@Lazy @Qualifier("eurekaClient") EurekaClient eurekaClient, RestTemplate restTemplate) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getInfo() {
        InstanceInfo instanceInfo = eurekaClient.getApplication(appName).getInstances().get(0);

        return String.format("(microservice-a)(GetInfo)(Application name: '%s')(Host: '%s')(Port: '%s')",
                instanceInfo.getAppName(), instanceInfo.getHostName(), instanceInfo.getPort());
    }

    @HystrixCommand(fallbackMethod = "getFallbackInfoByExternalMicroservice",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "20000"),
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "20000"),
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100")
            },
            threadPoolKey = "microserviceInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
            })
    @Override
    public String getInfoByExternalMicroservice(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    private String getFallbackInfoByExternalMicroservice(String url) {
        return "Fallback Method is here!!!";
    }
}
