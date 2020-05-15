package com.trl.microservicea.app;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppServiceImpl implements AppService {

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    public AppServiceImpl(@Lazy @Qualifier("eurekaClient") EurekaClient eurekaClient, RestTemplate restTemplate) {
        this.eurekaClient = eurekaClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public String checkConnectionBetweenMicroservices() {
        StringBuilder result = new StringBuilder();

        String fromMicroserviceA = getInfo();
        String fromMicroserviceB = restTemplate.getForObject("http://MICROSERVICE-B/app/getInfo", String.class);
        String fromMicroserviceC = restTemplate.getForObject("http://MICROSERVICE-C/app/getInfo", String.class);
        String fromMicroserviceD = restTemplate.getForObject("http://MICROSERVICE-D/app/getInfo", String.class);
        String fromMicroserviceE = restTemplate.getForObject("http://MICROSERVICE-E/app/getInfo", String.class);


        result.append(fromMicroserviceA);
        result.append("\n");
        result.append(fromMicroserviceB);
        result.append("\n");
        result.append(fromMicroserviceC);
        result.append("\n");
        result.append(fromMicroserviceD);
        result.append("\n");
        result.append(fromMicroserviceE);

        return result.toString();
    }

    @Override
    public String getInfo() {
        InstanceInfo instanceInfo = eurekaClient.getApplication(appName).getInstances().get(0);

        return String.format("(microservice-a)(GetInfo)(Application name: '%s')(Host: '%s')(Port: '%s')",
                instanceInfo.getAppName(), instanceInfo.getHostName(), instanceInfo.getPort());
    }

    @HystrixCommand(fallbackMethod = "callNonExistentMicroserviceFallback")
    @Override
    public String callNonExistentMicroservice() {
        StringBuilder result = new StringBuilder();


        String fromUnknownMicroservice = restTemplate.getForObject("http://UnknownMicroservice/app/getInfo", String.class);

        result.append(fromUnknownMicroservice);

        return result.toString();
    }

    public String callNonExistentMicroserviceFallback() {

        return "Fallback Method is here!!!";
    }
}

