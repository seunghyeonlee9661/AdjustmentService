package com.sparta.adjust.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class AdjustController {
    @GetMapping("/test/adjust")
    public String hello() {
        return "Hello, this is Adjustment Controller";
    }
}