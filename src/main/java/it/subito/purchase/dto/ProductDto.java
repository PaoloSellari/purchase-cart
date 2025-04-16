package it.subito.purchase.dto;

import it.subito.purchase.entity.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Data Transfer Object representing a product.
 * This class encapsulates the information related to a single product.
 */
@Value
public class ProductDto implements Serializable {
    /**
     * Represents product unique identifier. Ignored on Product creation or update.
     */
    Long productId;
    /**
     * Represents the name or brief textual description of a product.
     * This field is constrained to a maximum length of 255 characters.
     * It is typically used for identifying or describing a product in a concise manner.
     */
    @Size(max = 255)

    /**
     * Represents product brief textual description.
     */
    String name;

    /**
     * Represents the Value-Added Tax (VAT) rate applicable to a product.
     * This value is expressed as a percentage and determines the amount of VAT
     * that needs to be applied to the product's price.
     */
    @Min(0)
    @Max(100)
    Integer vatRate;

    /**
     * Represents the unit price of a product.
     * This value indicates the individual cost of a single unit of the product.
     * Typically expressed as a monetary value with precision and scale.
     */
    BigDecimal unitPrice;
}