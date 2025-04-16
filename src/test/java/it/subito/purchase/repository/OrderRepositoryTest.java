package it.subito.purchase.repository;

import it.subito.purchase.mapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Slf4j
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private ModelMapper modelMapper;

    /**
     * This test is based on test data loaded in V01.1__test_data.sql where orders have not yet updated pricing.
     * It runs db updates and checks against expected values
     */
    @ParameterizedTest
    @CsvSource(delimiterString = " ", value = {"1 31.700 1.268", "2 24.400 2.440"})
    void testUpdateOrderItemPricingByOrderId(Long givenOrdeId, BigDecimal expectedPrice, BigDecimal expectedVat) {
        assertThat(orderRepository.updateOrderItemPricingByOrderId(givenOrdeId), is(greaterThan(0)));
        assertThat(orderRepository.updateOrderPricingByOrderId(givenOrdeId), is(greaterThan(0)));

        var order = orderRepository.findById(givenOrdeId).orElseThrow();
        assertThat(order.getOrderPrice(), is(expectedPrice));
        assertThat(order.getOrderVat(), is(expectedVat));

        var orderDto = modelMapper.toDto(order);
        orderDto.getItems().forEach(itemDto -> {
            log.info("item {}", itemDto);
        });
        log.info("order {}", orderDto);
    }

}