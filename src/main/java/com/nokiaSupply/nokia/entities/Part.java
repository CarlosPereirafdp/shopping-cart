package com.nokiaSupply.nokia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Entity
@Table(name = "parts")
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Integer id;

    @Setter
    @Column(name = "part_name")
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @Setter
    @Column(name = "price")
    private double price;
    @Setter
    @Column(name = "sold")
    private boolean sold;

    public Part(Integer id, String name, Manufacturer manufacturer, double price, boolean sold) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.sold = sold;
    }

    public Part() {

    }
}
