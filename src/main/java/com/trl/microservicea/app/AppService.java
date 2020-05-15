package com.trl.microservicea.app;

public interface AppService {

    String checkConnectionBetweenMicroservices ();

    String getInfo();

    String callNonExistentMicroservice();
}
