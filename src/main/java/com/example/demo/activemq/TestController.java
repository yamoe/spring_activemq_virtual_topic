package com.example.demo.activemq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {

    private final EmailProducer emailProducer;

    @GetMapping("/test")
    public ResponseEntity<?> produce() {
        emailProducer.sendVirtualTopic("hkkim@digiparts.co.kr", "hi man");
        return ResponseEntity.ok("");
    }

}
