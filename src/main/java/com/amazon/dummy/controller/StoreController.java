package com.amazon.dummy.controller;

import com.amazon.dummy.entity.Store;
import com.amazon.dummy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StoreController {

    @Autowired
    private StoreService storeService;
    @GetMapping("/")
    public String home() {
        System.out.println("Controller method called");
        return "home";
    }

    @GetMapping("/sign_in")
    public String singIn() {
        System.out.println("Here");
        return "signin";
    }

    @GetMapping("/search")
    public String search(@RequestParam String category, @RequestParam String search, Model model) {
        model.addAttribute("searchKeyword", search);
        Store[] stores = storeService.getProductsByKeyword(search);
        model.addAttribute("products",stores);
//        System.out.println(category+" "+search+" "+stores);
        return "products";
    }

}