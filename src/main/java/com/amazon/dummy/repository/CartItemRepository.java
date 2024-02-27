package com.amazon.dummy.repository;

import com.amazon.dummy.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

}