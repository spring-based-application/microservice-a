package com.trl.microservicea.hello;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post() {
        ResponseEntity<String> response = null;

        return response;
    }

    @GetMapping(
            path = "/{helloId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get() {
        ResponseEntity<String> response = null;

        return response;
    }

    @PatchMapping(path = "/{helloId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patch() {
        ResponseEntity<String> response = null;

        return response;
    }

    @DeleteMapping(
            path = "/{helloId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete() {
        ResponseEntity<String> response = null;


        return response;
    }
}
