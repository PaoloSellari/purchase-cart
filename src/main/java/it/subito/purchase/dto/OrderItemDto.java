package it.subito.purchase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.subito.purchase.entity.OrderItem;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Data Transfer Object representing an order item.
 * This class is used to encapsulate the data related to a single item in an order.
 * Each order item is linked to a specific product, has a quantity, a price, and a VAT amount.
 */
@Value
public class OrderItemDto implements Serializable {
    @JsonIgnore
    Long id;

    /**
     * Represents the unique identifier of the associated order.
     * This field serves as a reference to the parent order to which the item belongs.
     */
    Long orderId;

    /**
     * Represents the unique identifier of the associated product.
     * This field acts as a reference to a specific product and is used to establish
     * the linkage between an order item and the corresponding product entity.
     */
    Long productId;

    /**
     * Represents the quantity of the associated product in the order item.
     * This field indicates the number of units of the product included in the order.
     */
    Integer quantity;

    /**
     * Represents the price of the product, for specified {@link OrderItemDto#quantity}.
     */
    BigDecimal price;

    /**
     * Represents the Value-Added Tax (VAT) amount applicable to the order item, for specified {@link OrderItemDto#quantity}.
     */
    BigDecimal vat;
}