package com.example.Vlarionov.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Premise")
public class Premise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String meaning;
    public String square;

    @OneToMany(mappedBy = "premise", fetch = FetchType.EAGER)
    private Collection<furniture> furniture;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getmeaning() {
        return meaning;
    }
    public void setmeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getsquare() {
        return square;
    }
    public void setsquare(String square) {
        this.square = square;
    }

    public Collection<furniture> getfurniture() {
        return furniture;
    }
    public void setemployees(Collection<furniture> employees) {
        this.furniture = furniture;
    }



    public Premise() { }

    public Premise(String meaning, String square) {
        this.meaning = meaning;
        this.square = square;

    }
}
