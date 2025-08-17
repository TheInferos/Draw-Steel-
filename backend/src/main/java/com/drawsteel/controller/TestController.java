package com.drawsteel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public String test() {
        return "Hello from Draw Steel Backend! Everything is working!";
    }
    
    @GetMapping("/")
    public String home() {
        return "Draw Steel Backend is running successfully!";
    }
}
