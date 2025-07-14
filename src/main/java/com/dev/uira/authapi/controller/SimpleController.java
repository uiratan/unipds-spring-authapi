package com.dev.uira.authapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/open")
    public String sayHello() {
        return "Hello World from open endpoint";
    }

    @GetMapping("/restricted")
    public String sayHelloRestricted() {
        return "Hello World from restricted endpoint";
    }
}
