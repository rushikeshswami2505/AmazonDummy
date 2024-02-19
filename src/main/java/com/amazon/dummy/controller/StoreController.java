package com.amazon.dummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/home")
    public String home() {
        System.out.println("Controller method called");
        return "home";
    }

    @GetMapping("/sign_in")
    public String singIn() {
        System.out.println("Here");
        return "signin";
    }

}