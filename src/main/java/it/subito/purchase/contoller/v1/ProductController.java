package it.subito.purchase.contoller.v1;

import it.subito.purchase.dto.ProductRequestDto;
import it.subito.purchase.dto.ProductDto;
import it.subito.purchase.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST API endpoints related to managing products.
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /**
     * Lettera e testamento
     *
     * @param request
     * @return
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ProductDto createOrder(@RequestBody ProductRequestDto request) {
        return productService.createProduct(request.getProduct());
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Stream<ProductDto> getOrders() {
        return productService.getProducts();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ProductDto getOrders(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping(path = "/{id}")
    void deleteOrder(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ProductDto updateOrder(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        return productService.updateProductById(id, request.getProduct());
    }


}
