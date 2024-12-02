package com.ibeus.Comanda.Digital.DTO.order;

import com.ibeus.Comanda.Digital.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(Long id, Long clientId, String nomeClient, String phone, String cep, String number, Double valor, OrderStatus status, LocalDateTime timestamp, List<ItemOrderDTO> itens, String description) {
}
