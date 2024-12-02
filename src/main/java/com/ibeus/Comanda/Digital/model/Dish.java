package com.ibeus.Comanda.Digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "dishes")
@Data
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @OneToMany(mappedBy = "dish")
    @JsonIgnore
    private List<ItemOrder> itens;
}