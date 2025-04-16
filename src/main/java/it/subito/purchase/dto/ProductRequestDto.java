package it.subito.purchase.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Data Transfer Object representing a product request.
 * This class is used to encapsulate the data required for creating
 * or updating a product in the system. It contains a single field
 * of type {@code ProductDto}, which provides the details of the product.
 */
@Value
@Builder
@Jacksonized
public class ProductRequestDto {
    ProductDto product;
}
