package com.amazon.dummy.controller;

import com.amazon.dummy.entity.CartItem;
import com.amazon.dummy.entity.Store;
import com.amazon.dummy.service.CartItemService;
import com.amazon.dummy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoreController {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private StoreService storeService;
    @GetMapping("/") //INITIAL LOAD
    public String home() {
        System.out.println("Controller method called");
        return "home";
    }

    @GetMapping("/sign_in") //NOT UESED
    public String singIn() {
        System.out.println("Here");
        return "signin";
    }

    @GetMapping("/search") //FROM HOME
    public String search(@RequestParam String category, @RequestParam String search, Model model) {
        Store[] stores = storeService.getProductsByKeyword(search);
        model.addAttribute("products",stores);
        return "products";
    }

    @GetMapping("/product_detail/{productId}") //FROM PRODUCTS
    public String productDetail(@PathVariable int productId, Model model) {
        Store product = storeService.getProductById(productId);
        if (product == null) {
            // Handle the case where the product with the given ID is not found
            return "error";
        }
        model.addAttribute("product", product);
        System.out.println("Here reached");
        return "product_view";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addCart(@RequestParam int quantity,
    @PathVariable int productId) {
        // Your implementation here
        System.out.println(productId+" Save called "+quantity);
        cartItemService.addToCart(1L, (long) productId, quantity);
        return "redirect:/";
    }


//    @ModelAttribute("userId") Long userId

}