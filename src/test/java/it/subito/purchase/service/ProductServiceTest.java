package it.subito.purchase.service;

import it.subito.purchase.entity.Product;
import it.subito.purchase.exception.ResourceNotFoundException;
import it.subito.purchase.mapper.ModelMapper;
import it.subito.purchase.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProductServiceTest {


    @MockitoSpyBean
    private ProductRepository productRepository;

    @MockitoSpyBean
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;


    @InjectMocks
    @Autowired
    private ProductService productService;


    @Test
    void createProduct_ShouldCreateAndReturnProduct() {

        var product = Product
                .builder()
                .name("grapefruit")
                .vatRate(4)
                .unitPrice(BigDecimal.valueOf(0.98))
                .build();

        var productDto = modelMapper.toDto(product);

        var result = productService.createProduct(productDto);
        verify(productRepository).saveAndFlush(ArgumentMatchers.any());

        assertThat(result, is(notNullValue()));
        assertThat(result.getProductId(), is(notNullValue()));
        assertThat(result.getVatRate(), is(4));
        assertThat(result.getUnitPrice().doubleValue(), is(0.98));
    }

    @Test
    void updateProductById_ShouldUpdateAndReturnProduct() {

        var product = Product
                .builder()
                .name("grapefruit")
                .vatRate(4)
                .unitPrice(BigDecimal.valueOf(0.98))
                .build();

        var productDto = modelMapper.toDto(product);
        var result = productService.updateProductById(1L, productDto);

        verify(productRepository).saveAndFlush(ArgumentMatchers.any());

        assertThat(result, is(notNullValue()));
        assertThat(result.getProductId(), is(notNullValue()));
        assertThat(result.getVatRate(), is(4));
        assertThat(result.getUnitPrice().doubleValue(), is(0.98));

    }
    @Test
    void deleteProductById_ShouldDeleteProduct() {
        productService.deleteProductById(7L);
        verify(productRepository).deleteById(7L);
        assertThat(productRepository.findById(7L).isEmpty(),is(true));
    }

    @Test
    void getProductById_ShouldReturnProduct() {
        var order = productService.getProductById(1L);
        assertThat(order, is(notNullValue()));
        assertThat(order.getProductId(), is(1L));
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(72L));
    }

    @Test
    void getOrders_ShouldReturnOrders() {
        var orders = productService.getProducts();
        assertThat(orders.count(), is(7L));
    }

}