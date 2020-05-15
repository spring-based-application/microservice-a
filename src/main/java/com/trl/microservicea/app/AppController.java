package com.trl.microservicea.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/app")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping(
            path = "/getInfo",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getInfo() {

        String result = appService.getInfo();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(
            path = "/checkConnectionBetweenMicroservices",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkConnectionBetweenMicroservices () {

        String result = appService.checkConnectionBetweenMicroservices();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create() {

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PatchMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody String value) {

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
