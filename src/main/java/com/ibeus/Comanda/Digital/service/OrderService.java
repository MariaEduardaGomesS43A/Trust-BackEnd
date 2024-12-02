package com.ibeus.Comanda.Digital.service;
import com.ibeus.Comanda.Digital.DTO.order.ItemOrderDTO;
import com.ibeus.Comanda.Digital.DTO.order.OrderRequestDTO;
import com.ibeus.Comanda.Digital.DTO.order.OrderResponseDTO;
import com.ibeus.Comanda.Digital.DTO.order.OrderStatusDTO;
import com.ibeus.Comanda.Digital.exception.ClientNotFoundException;
import com.ibeus.Comanda.Digital.exception.DishNotFoundException;
import com.ibeus.Comanda.Digital.exception.OrderNotFoundException;
import com.ibeus.Comanda.Digital.model.Client;
import com.ibeus.Comanda.Digital.model.Dish;
import com.ibeus.Comanda.Digital.model.ItemOrder;
import com.ibeus.Comanda.Digital.model.Order;
import com.ibeus.Comanda.Digital.model.enums.OrderStatus;
import com.ibeus.Comanda.Digital.repository.ClientRepository;
import com.ibeus.Comanda.Digital.repository.DishRepository;
import com.ibeus.Comanda.Digital.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DishRepository dishRepository;


    public List<OrderResponseDTO> findAll() {
        List<Order> orders = repository.findAll();
        return convertOrdersToDTOs(orders);
    }

    public OrderResponseDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(OrderNotFoundException::new);
        return transformDTO(order);
    }
    public OrderResponseDTO create(OrderRequestDTO orderDTO) {
        Order order = new Order();
        Client client = clientRepository.findById(orderDTO.clientId())
                        .orElseThrow(ClientNotFoundException::new);
        order.setClient(client);

        List<ItemOrder> items = convertItemOrderDTOsToEntities(orderDTO.itens(), order);
        double totalValue = items.stream().mapToDouble(item -> item.getDish().getPrice() * item.getQuantity()).sum();

        order.setValor(totalValue);
        order.setStatus(OrderStatus.PREPARING);
        order.setTimestamp(LocalDateTime.now());
        order.setItens(items);
        order.setDescription(orderDTO.description());

        repository.save(order);
        return transformDTO(order);
    }
    public OrderResponseDTO update(Long id, OrderRequestDTO orderDTO) {
        Order order = repository.findById(id).orElseThrow(OrderNotFoundException::new);

        List<ItemOrder> items = convertItemOrderDTOsToEntities(orderDTO.itens(), order);
        double totalValue = items.stream().mapToDouble(item -> item.getDish().getPrice() * item.getQuantity()).sum();


        order.setId(id);
        order.setValor(totalValue);
        order.setTimestamp(LocalDateTime.now());
        order.setStatus(OrderStatus.PREPARING);
        order.setItens(items);
        order.setDescription(orderDTO.description());

        repository.save(order);
        return transformDTO(order);
    }
    public List<OrderResponseDTO> findByStatus(OrderStatus status) {
        List<Order> orders = repository.findByStatus(status);
        return convertOrdersToDTOs(orders);
    }


    public OrderStatus findStatus(Long id){
        OrderResponseDTO responseDTO = findById(id);
        return responseDTO.status();
    }
    public void updateDescription(Long id, String description){
        Order order = repository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.setDescription(description);
        repository.save(order);
    }
    public void updateStatus(Long id, OrderStatusDTO code){
        Order order = repository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.setStatus(OrderStatus.valueOf(code.code()));
        repository.save(order);
    }

    public void delete(Long id) {
        Order order = repository.findById(id).orElseThrow(OrderNotFoundException::new);
        repository.delete(order);
    }

    public OrderResponseDTO transformDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getClient().getId(),
                order.getClient().getName(),
                order.getClient().getPhone(),
                order.getClient().getCep(),
                order.getClient().getNumber(),
                order.getValor(),
                order.getStatus(),
                order.getTimestamp(),
                order.getItens().stream()
                        .map(item -> new ItemOrderDTO(
                                item.getDish().getId(),
                                item.getDish().getName(),
                                item.getQuantity()))
                        .collect(Collectors.toList()),
                order.getDescription()
        );
    }

    public List<OrderResponseDTO> convertOrdersToDTOs(List<Order> orders) {
        return orders.stream()
                .map(this::transformDTO)
                .collect(Collectors.toList());
    }

    public List<ItemOrder> convertItemOrderDTOsToEntities(List<ItemOrderDTO> itemOrderDTOs, Order order) {
        return itemOrderDTOs.stream().map(itemDTO -> {
            Dish dish = dishRepository.findById(itemDTO.dishId())
                    .orElseThrow(DishNotFoundException::new);

            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setDish(dish);
            itemOrder.setQuantity(itemDTO.quantity());
            itemOrder.setOrder(order);

            return itemOrder;
        }).collect(Collectors.toList());
    }
}
