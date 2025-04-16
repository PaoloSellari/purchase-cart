package it.subito.purchase.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object representing an order.
 * This class encapsulates the information related to a single order.
 */
@Value
public class OrderDto implements Serializable {
    /**
     * Represents order unique identifier. Ignored on Order creation or update.
     */
    Long orderId;

    /**
     * Represents the total price of the order.
     * This value is computed as the sum of the prices of all the order items,
     * excluding any taxes.
     */
    BigDecimal orderPrice;

    /**
     * Represents the total Value-Added Tax (VAT) amount applicable to the order.
     * This value is calculated as the aggregated VAT across all order items
     * for financial calculations. It provides the amount of tax applied to the
     * {@code orderPrice} based on the VAT rates of the associated products.
     */
    BigDecimal orderVat;

    /**
     * Represents the collection of items included in the order.
     * Each item in the collection is represented as an instance of {@link OrderItemDto}.
     * This collection contains details about the products, quantities, prices,
     * and applicable VAT for each item associated with the order.
     */
    List<OrderItemDto> items;
}