package com.nokiaSupply.nokia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
@Table(name = "manufacturer")
public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Integer id;

    @Setter
    @Column(name = "manufacturer_name")
    private String name;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Part> partsList = new ArrayList<>();

    public Manufacturer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer() {
    }
}
