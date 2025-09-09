package com.rentalsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Rental System API!";
    }

    @GetMapping("/status")
    public String status() {
        return "Rental System API is running ✅";
    }
}
