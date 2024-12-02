package com.ibeus.Comanda.Digital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String phone;
    @Column(nullable = false)
    @NotBlank
    private String cep;
    @Column(nullable = false)
    @NotBlank
    private String number;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();
}
