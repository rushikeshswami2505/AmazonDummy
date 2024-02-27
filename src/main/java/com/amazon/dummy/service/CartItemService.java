package com.amazon.dummy.service;

import com.amazon.dummy.entity.CartItem;
import com.amazon.dummy.entity.Store;
import com.amazon.dummy.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem existingCartItem(Long userId,Long productId){
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId,productId);
        return existingCartItem.orElseGet(CartItem::new);
    }
    public void addToCart(Long userId, Long productId, int quantity) {
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId,productId);

        if(existingCartItem.isPresent()){
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
        else {
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}
