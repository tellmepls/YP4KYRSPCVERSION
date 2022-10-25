package com.example.Vlarionov.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String typeSeparation;

    @ManyToMany
    @JoinTable(name = "shop_furniture",
            joinColumns = @JoinColumn(name ="shop_id"),
            inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private List<furniture> furniture;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String Address) { this.address = Address; }

    public String gettypeSeparation() {
        return typeSeparation;
    }
    public void settypeSeparation(String Schedule) {
        this.typeSeparation = typeSeparation;
    }

    public shop() { }

    public shop(String address, String typeSeparation) {
        this.address = address;
        this.typeSeparation = typeSeparation;

    }
}
