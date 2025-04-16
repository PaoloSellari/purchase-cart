package it.subito.purchase.contoller.v1;

import it.subito.purchase.dto.ProductRequestDto;
import it.subito.purchase.dto.ProductDto;
import it.subito.purchase.exception.ResourceNotFoundException;
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
     * Creates a new product based on the provided product details.
     *
     * @param request the DTO containing the product details to be created
     * @return the DTO representing the newly created product
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ProductDto createOrder(@RequestBody ProductRequestDto request) {
        return productService.createProduct(request.getProduct());
    }

    /**
     * Retrieves a stream of all products.
     *
     * @return a stream of {@code ProductDto}, each representing a product
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Stream<ProductDto> getOrders() {
        return productService.getProducts();
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id the unique identifier of the product to retrieve
     * @return the {@code ProductDto} representing the product with the specified ID
     * @throws ResourceNotFoundException if no product is found with the specified ID
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    ProductDto getOrders(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * @param id the unique identifier of the product to delete
     */
    @DeleteMapping(path = "/{id}")
    void deleteOrder(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    /**
     * Updates an existing product with the specified ID using the provided product details.
     *
     * @param id the unique identifier of the product to be updated
     * @param request the DTO containing the updated product details
     * @return the DTO representing the updated product
     */
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ProductDto updateOrder(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        return productService.updateProductById(id, request.getProduct());
    }


}
