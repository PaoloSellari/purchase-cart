package it.subito.purchase.service;

import it.subito.purchase.dto.OrderItemDto;
import it.subito.purchase.entity.Order;
import it.subito.purchase.entity.OrderItem;
import it.subito.purchase.exception.ResourceNotFoundException;
import it.subito.purchase.mapper.ModelMapper;
import it.subito.purchase.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderServiceTest {


    @MockitoSpyBean
    private OrderRepository orderRepository;

    @MockitoSpyBean
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;


    @InjectMocks
    @Autowired
    private OrderService orderService;


    @Test
    void createOrder_ShouldCreateAndReturnOrder() {

        var order = Order
                .builder()
                .item(OrderItem.builder().productId(1L).quantity(1).build())
                .item(OrderItem.builder().productId(2L).quantity(2).build())
                .build();

        var orderDto = modelMapper.toDto(order);

        var result = orderService.createOrder(orderDto);

        verify(orderRepository).updateOrderItemPricingByOrderId(3L);
        verify(orderRepository).updateOrderPricingByOrderId(3L);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), is(notNullValue()));
        assertThat(result.getOrderVat(), is(notNullValue()));
        assertThat(result.getOrderPrice(), is(notNullValue()));
    }

    @Test
    void updateOrderById_ShouldUpdateAndReturnOrder() {

        var order = Order
                .builder()
                .item(OrderItem.builder().productId(5L).quantity(1).build())
                .item(OrderItem.builder().productId(6L).quantity(2).build())
                .build();

        var orderDto = modelMapper.toDto(order);
        var result = orderService.updateOrderById(1L, orderDto);

        verify(orderRepository).updateOrderItemPricingByOrderId(1L);
        verify(orderRepository).updateOrderPricingByOrderId(1L);

        assertThat(result, is(notNullValue()));
        assertThat(result.getOrderId(), is(notNullValue()));
        assertThat(result.getOrderVat(), is(notNullValue()));
        assertThat(result.getOrderPrice(), is(notNullValue()));

        var products = result.getItems().stream().map(OrderItemDto::getProductId).toList();
        assertThat(products, containsInAnyOrder(5L,6L));
        assertThat(products, not(contains(1L,2L)));


    }

    @Test
    void deleteOrderById_ShouldDeleteOrder() {
        orderService.deleteOrderById(1L);
        verify(orderRepository).deleteById(1L);
        assertThat(orderRepository.findById(1L).isEmpty(),is(true));
    }

    @Test
    void getOrderById_ShouldReturnOrder() {
        var order = orderService.getOrderById(1L);
        assertThat(order, is(notNullValue()));
        assertThat(order.getOrderId(), is(1L));
        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderById(3L));
    }

    @Test
    void getOrders_ShouldReturnOrders() {
        var orders = orderService.getOrders();
        assertThat(orders.count(), is(2L));
    }
}