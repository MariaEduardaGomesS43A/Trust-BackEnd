package com.ibeus.Comanda.Digital.DTO.order;

import java.util.List;

public record OrderRequestDTO(Long clientId, List<ItemOrderDTO> itens, String description) {
}
