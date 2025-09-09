package com.rentalsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
    @GetMapping({"/", "/ui"})
    public String home() {
        return "index";
    }
}
