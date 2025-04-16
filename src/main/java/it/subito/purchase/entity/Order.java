package it.subito.purchase.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;

    @Column(name = "ORDER_PRICE", precision = 19, scale = 3)
    private BigDecimal orderPrice;

    @Column(name = "ORDER_VAT", precision = 19, scale = 3)
    private BigDecimal orderVat;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    @Singular
    private List<OrderItem> items = new LinkedList<>();

}