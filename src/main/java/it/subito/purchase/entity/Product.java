package it.subito.purchase.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Size(max = 255)
    @Column(name = "NAME")
    private String name;

    @Column(name = "VAT_RATE")
    private Integer vatRate;

    @Column(name = "UNIT_PRICE", precision = 19, scale = 3)
    private BigDecimal unitPrice;

}