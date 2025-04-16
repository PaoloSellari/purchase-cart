package it.subito.purchase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Data Transfer Object representing an order request.
 * This class is used to encapsulate the data required for creating
 * or updating an order in the system. It contains a single field
 * of type {@code OrderDto}, which provides the details of the order.
 */
@Value
@Builder
@Jacksonized
public class OrderRequestDto {
    @JsonIgnoreProperties({"orderId"})
    OrderDto order;
}
