package it.subito.purchase.repository;

import it.subito.purchase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
            update ORDERS ord
            SET ORDER_PRICE = (SELECT SUM(PRICE) FROM ORDER_ITEMS it WHERE it.ORDER_ID = ord.ORDER_ID),
                ORDER_VAT = (SELECT SUM(VAT) FROM ORDER_ITEMS it WHERE it.ORDER_ID = ord.ORDER_ID)
            where ord.ORDER_ID = :orderId
            """)
    Integer updateOrderPricingByOrderId(Long orderId);

    @Modifying
    @Query(nativeQuery = true,
            value = """
                    update ORDER_ITEMS it
                    SET PRICE = QUANTITY * (SELECT pr.UNIT_PRICE FROM PRODUCTS pr WHERE pr.PRODUCT_ID = it.PRODUCT_ID),
                        VAT=QUANTITY * (SELECT pr.UNIT_PRICE * pr.VAT_RATE / 100 FROM PRODUCTS pr WHERE pr.PRODUCT_ID = it.PRODUCT_ID)
                    where it.ORDER_ID = :orderId
                    """)
    Integer updateOrderItemPricingByOrderId(Long orderId);

}