package com.punyadev.springkafkaconsumerdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final AppProducer appProducer;

    public AppController(AppProducer appProducer) {
        this.appProducer = appProducer;
    }

    @PostMapping("/api/send-data")
    public void postProducer(){
        appProducer.sendMessage("test", "hello world");
    }
}
