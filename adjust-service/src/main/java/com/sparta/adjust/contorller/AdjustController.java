package com.sparta.adjust.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adjust")
class AdjustController {
    @GetMapping("/test")
    public String hello() {
        return "Hello, this is Adjustment Controller";
    }
}