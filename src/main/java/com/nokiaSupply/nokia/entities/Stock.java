package com.nokiaSupply.nokia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Entity
@Table(name = "quantities")
public class Stock implements Serializable {
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
    @Column(name = "quantity")
    private Integer quantity;

    public Stock() {

    }

    public Stock(Integer id, Integer partId, Integer manufacturerId, Integer quantity) {
        this.id = id;
        this.partId = partId;
        this.manufacturerId = manufacturerId;
        this.quantity = quantity;
    }
}
