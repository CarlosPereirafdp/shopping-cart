package com.nokiaSupply.nokia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "shop_cart")
public class ShopCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    @Column(name = "part_id")
    private Integer partId;

    @Setter
    @Column(name = "manufacturer_id")
    private Integer manufacturerId;

    @Setter
    @Column(name = "parts_quantity")
    private Integer partsQuantity;

    @Setter
    @Column(name = "total_price")
    private double totalPrice;

    public ShopCart() {
    }
}
