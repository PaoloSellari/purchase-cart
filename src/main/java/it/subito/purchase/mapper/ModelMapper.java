package it.subito.purchase.mapper;

import it.subito.purchase.dto.OrderDto;
import it.subito.purchase.dto.OrderItemDto;
import it.subito.purchase.dto.ProductDto;
import it.subito.purchase.entity.Order;
import it.subito.purchase.entity.OrderItem;
import it.subito.purchase.entity.Product;
import org.mapstruct.*;

/**
 * Abstract class providing mapping functionalities between entity and Data Transfer Object (DTO).
 * This class is annotated as a MapStruct mapper and defines methods for converting between
 * entity classes and their corresponding DTOs, as well as performing partial updates on entities.
 *
 * Key functionalities:
 * - Conversion between entity and DTO for Product, OrderItem, and Order.
 * - Partial updates to existing entities without overwriting properties not included in the update.
 *
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ModelMapper {


    public abstract Product toEntity(ProductDto productDto);

    public abstract ProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product partialUpdate(ProductDto productDto, @MappingTarget Product product);

    abstract OrderItem toEntity(OrderItemDto orderItemDto);

    public abstract OrderItemDto toDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract OrderItem partialUpdate(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);

    public abstract Order toEntity(OrderDto orderDto);


    public abstract OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);
}
