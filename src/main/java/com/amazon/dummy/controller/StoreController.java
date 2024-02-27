package com.amazon.dummy.controller;

import com.amazon.dummy.entity.CartItem;
import com.amazon.dummy.entity.Store;
import com.amazon.dummy.service.CartItemService;
import com.amazon.dummy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
            return "error";
        }
        CartItem cartItem = cartItemService.existingCartItem(1L,(long)productId);
        model.addAttribute("quantity",cartItem.getQuantity());
        model.addAttribute("product", product);
        if(cartItem.getQuantity()>0) model.addAttribute("purchased",true);
        else model.addAttribute("purchased",false);

        System.out.println("Here reached");
        return "product_view";
    }

    @PostMapping("/add-to-cart/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Indicates a successful request with no additional content
    public void addCart(@RequestParam int quantity, @PathVariable int productId) {
        // Your implementation here
        System.out.println(productId + " Save called " + quantity);
        cartItemService.addToCart(1L, (long) productId, quantity);
    }

    @GetMapping("/cart-list")
    public String productList(Model model){
        List<CartItem> carts =  cartItemService.getAllCartItems();
        Collections.sort(carts, Comparator.comparingLong(CartItem::getProductId));
        List<Store> cartItemsDetail = storeService.getProductsByList(carts);
        Collections.sort(cartItemsDetail, Comparator.comparingInt(Store::getId));

        for(int i=0;i<cartItemsDetail.size();i++){
            cartItemsDetail.get(i).setQuantity(carts.get(i).getQuantity());
//            System.out.println(cartItemsDetail.get(i).getId()+" "+ carts.get(i).getProductId() +" "+carts.get(i).getQuantity());
        }
        model.addAttribute("cartItems",cartItemsDetail);
        model.addAttribute("carts",carts);
        return "cart_list";
    }
}