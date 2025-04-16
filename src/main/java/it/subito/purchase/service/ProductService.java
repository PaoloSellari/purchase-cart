package it.subito.purchase.service;

import it.subito.purchase.dto.ProductDto;
import it.subito.purchase.exception.ResourceNotFoundException;
import it.subito.purchase.mapper.ModelMapper;
import it.subito.purchase.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public Stream<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(modelMapper::toDto);
    }

    public ProductDto createProduct(ProductDto productDto) {
        var product = modelMapper.toEntity(productDto);
        productRepository.saveAndFlush(product);
        return modelMapper.toDto(product);
    }

    public ProductDto updateProductById(Long productId, ProductDto productDto) {
        var product = productRepository.findById(productId).orElseThrow(ResourceNotFoundException::new);
        modelMapper.partialUpdate(productDto, product);
        product.setProductId(productId);
        productRepository.saveAndFlush(product);
        return modelMapper.toDto(product);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId).map(modelMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

}
