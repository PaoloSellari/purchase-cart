package it.subito.purchase.service;

import it.subito.purchase.dto.OrderDto;
import it.subito.purchase.exception.ResourceNotFoundException;
import it.subito.purchase.entity.*;
import it.subito.purchase.mapper.ModelMapper;
import it.subito.purchase.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {


    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @PersistenceContext // This annotation ensures
    private final EntityManager entityManager;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = modelMapper.toEntity(orderDto);
        orderRepository.saveAndFlush(order);
        orderRepository.updateOrderItemPricingByOrderId(order.getOrderId());
        orderRepository.updateOrderPricingByOrderId(order.getOrderId());
        entityManager.refresh(order);
        return modelMapper.toDto(order);
    }

    public Stream<OrderDto> getOrders() {
        return orderRepository.findAll().stream()
                .map(modelMapper::toDto);
    }

    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(modelMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto updateOrderById(Long id, OrderDto orderDto) {
        var order = orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        modelMapper.partialUpdate(orderDto, order);
        orderRepository.saveAndFlush(order);
        orderRepository.updateOrderItemPricingByOrderId(order.getOrderId());
        orderRepository.updateOrderPricingByOrderId(order.getOrderId());
        entityManager.refresh(order);
        return modelMapper.toDto(order);
    }
}
