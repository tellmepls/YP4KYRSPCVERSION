package com.example.Vlarionov.Models;

import javax.persistence.*;

@Entity
@Table(name = "DopInformation")
public class dopInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String design;
    public String numberOfLockers;
    @OneToOne(optional = true, mappedBy = "DopInformation")
    private furniture owner;

    public furniture getOwner() {return owner;}
    public void setOwner(furniture owner){this.owner = owner;}

    public dopInformation() { }

    public dopInformation(String design, String numberOfLockers) {
        this.design = design;
        this.numberOfLockers = numberOfLockers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getdesign() {
        return design;
    }

    public void setdesign(String design) {
        this.design = design;
    }

    public String getnumberOfLockers() {
        return numberOfLockers;
    }

    public void setnumberOfLockers(String numberOfLockers) {
        this.numberOfLockers = numberOfLockers;
    }
}
