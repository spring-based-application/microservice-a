package com.trl.microservicea.appInfo;

public interface AppInfoService {

    String getInfo();

    String getInfoByExternalMicroservice(String url);
}
