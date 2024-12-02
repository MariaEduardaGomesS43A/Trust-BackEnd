package com.ibeus.Comanda.Digital.DTO.client;

import com.ibeus.Comanda.Digital.model.Order;

import java.util.List;

public record ClientResponseDTO(Long id,String name, String phone, String cep, String number, List<Order> order) {
}
