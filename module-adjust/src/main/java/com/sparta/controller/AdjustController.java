package com.sparta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class AdjustController {
    @GetMapping("/test")
    public String hello() {
        return "Hello, this is Adjustment Controller";
    }
}