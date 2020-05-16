package com.trl.microservicea.app;

import com.trl.microservicea.appInfo.AppInfoService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {

    private final AppInfoService appInfoService;

    @Value("${microserviceB.url.getInfo}")
    private String microserviceB_URL_GetInfo;

    @Value("${microserviceC.url.getInfo}")
    private String microserviceC_URL_GetInfo;

    @Value("${microserviceD.url.getInfo}")
    private String microserviceD_URL_GetInfo;

    @Value("${microserviceE.url.getInfo}")
    private String microserviceE_URL_GetInfo;

    private String unknowMicroservice_URL_GetInfo = "http://UnknownMicroservice/app/getInfo";

    public AppServiceImpl(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Override
    public String checkConnectionBetweenMicroservices() {
        StringBuilder result = new StringBuilder();

        result.append(appInfoService.getInfo());
        result.append("\n");
        result.append(appInfoService.getInfoByExternalMicroservice(microserviceB_URL_GetInfo));
        result.append("\n");
        result.append(appInfoService.getInfoByExternalMicroservice(microserviceC_URL_GetInfo));
        result.append("\n");
        result.append(appInfoService.getInfoByExternalMicroservice(microserviceD_URL_GetInfo));
        result.append("\n");
        result.append(appInfoService.getInfoByExternalMicroservice(microserviceE_URL_GetInfo));

        return result.toString();
    }

    @Override
    public String checkFullback() {
        return appInfoService.getInfoByExternalMicroservice(unknowMicroservice_URL_GetInfo);
    }
}

