package com.nokiaSupply.nokia.repositories;


import com.nokiaSupply.nokia.entities.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShopCart, Integer> {
}
