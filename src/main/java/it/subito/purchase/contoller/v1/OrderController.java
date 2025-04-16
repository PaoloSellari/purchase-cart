package it.subito.purchase.contoller.v1;

import it.subito.purchase.exception.ResourceNotFoundException;
import it.subito.purchase.service.OrderService;
import it.subito.purchase.dto.OrderRequestDto;
import it.subito.purchase.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST API endpoints related to managing orders.
 */
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new order based on the provided order details.
     *
     * @param orderRequestDto the DTO containing the order details to be created
     * @return the DTO representing the newly created order
     */
    @PostMapping( consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    OrderDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto.getOrder());
    }

    /**
     * Retrieves a stream of all orders.
     *
     * @return a stream of {@code OrderDto}, each representing an order
     */
    @GetMapping( produces = APPLICATION_JSON_VALUE)
    Stream<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    /**
     * Retrieves an order by its unique identifier.
     *
     * @param id the unique identifier of the order to retrieve
     * @return the {@code OrderDto} representing the order with the specified ID
     * @throws ResourceNotFoundException if no order is found with the specified ID
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    OrderDto getOrders(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    /**
     * Deletes an order by its unique identifier.
     *
     * @param id the unique identifier of the order to delete
     */
    @DeleteMapping(path = "/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

    /**
     * Updates an existing order with the specified details.
     *
     * @param id the unique identifier of the order to update
     * @param orderRequestDto the DTO containing the updated order details
     * @return the updated order represented as an {@code OrderDto}
     */
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.updateOrderById(id, orderRequestDto.getOrder());
    }


}
