package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.DTO.order.OrderRequestDTO;
import com.ibeus.Comanda.Digital.DTO.order.OrderResponseDTO;
import com.ibeus.Comanda.Digital.DTO.order.OrderStatusDTO;
import com.ibeus.Comanda.Digital.model.enums.OrderStatus;
import com.ibeus.Comanda.Digital.repository.OrderRepository;
import com.ibeus.Comanda.Digital.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Operation(summary = "FindAll", description = "Esse metodo retorna todos os order")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO> > findAll() {
        List<OrderResponseDTO> orders = service.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @Operation(summary = "FindById", description = "Esse metodo retorna order por id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        OrderResponseDTO order = service.findById(id);
        return ResponseEntity.ok().body(order);
    }
    @Operation(summary = "Get Orders by Status", description = "Este método retorna os pedidos com um status específico")
    @GetMapping("/OrderByStatus/{statusCode}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByStatus(@PathVariable String statusCode) {
        OrderStatus status = OrderStatus.valueOf(statusCode.toUpperCase());
        List<OrderResponseDTO> orders = service.findByStatus(status);
        return ResponseEntity.ok().body(orders);
    }

    @Operation(summary = "createOrder", description = "Esse metodo faz a criação do order")
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequestDTO order) {
        OrderResponseDTO orderResponse = service.create(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderResponse.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "updateOrder", description = "Esse metodo faz a atualização do order")
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO order) {
        OrderResponseDTO orderUpdate = service.update(id, order);
        return ResponseEntity.ok().body(orderUpdate);
    }

    @Operation(summary = "FindStatus", description = "Esse metodo retorna status por id do order")
    @GetMapping("/status/{id}")
    public ResponseEntity<OrderStatus> findStatus(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findStatus(id));
    }

    @Operation(summary = "updateStatusOrder", description = "Esse metodo faz a atualização do status do order")
    @PutMapping("/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody OrderStatusDTO code) {
        service.updateStatus(id, code);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "updateDescription", description = "Esse metodo faz a atualização do description do order")
    @PutMapping("/description/{id}")
    public ResponseEntity<Void> updateDesc(@PathVariable Long id, @RequestBody String description){
        service.updateDescription(id,description);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "deleteOrder", description = "Esse metodo faz a deleção do order")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
