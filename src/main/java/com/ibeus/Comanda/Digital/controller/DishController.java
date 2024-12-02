package com.ibeus.Comanda.Digital.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ibeus.Comanda.Digital.model.Dish;
import com.ibeus.Comanda.Digital.service.DishService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dishes")
@CrossOrigin(origins = "http://localhost:4200")
public class DishController {

    @Autowired
    private DishService dishService;

    @Operation(summary = "FindAll", description = "Esse metodo retorna todos os dishes")
    @GetMapping
    public ResponseEntity<List<Dish>> findAll() {
        List<Dish> dishes = dishService.findAll();
        return ResponseEntity.ok().body(dishes);
    }

    @Operation(summary = "FindById", description = "Esse metodo retorna o dish por id")
    @GetMapping("/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id) {
        Dish dish = dishService.findById(id);
        return ResponseEntity.ok().body(dish);
    }

    @Operation(summary = "createDish", description = "Esse metodo faz a criação do dish")
    @PostMapping
    public ResponseEntity<Void> createDish(@RequestBody Dish dish) {
        dish = dishService.create(dish);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dish.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "updateDish", description = "Esse metodo faz a atualização do dish")
    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish dishUp = dishService.update(id, dish);
        return ResponseEntity.ok().body(dishUp);
    }

    @Operation(summary = "deleteDish", description = "Esse metodo faz a deleção do dish")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return ResponseEntity.noContent().build();
    }
}