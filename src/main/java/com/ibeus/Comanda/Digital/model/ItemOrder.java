package com.ibeus.Comanda.Digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "tb_itemOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
    @Positive
    private int quantity;
}
