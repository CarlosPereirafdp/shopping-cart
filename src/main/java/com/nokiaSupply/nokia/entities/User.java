package com.nokiaSupply.nokia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column
    private String name;

    @Setter
    @Column
    private double budget;

    public User() {
    }

    public User(Integer id, String name, double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }
}
