package com.programming.techie.productservice.service;

import com.programming.techie.productservice.repository.ProductRepository;
import com.programming.techie.productservice.dto.ProductResponse;
import com.programming.techie.productservice.model.Product;
import org.springframework.stereotype.Service;

import com.programming.techie.productservice.dto.ProductRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
	Product product = Product.builder()
			.name(productRequest.getName())
			.description(productRequest.getDescription())
			.price(productRequest.getPrice())
			.build();

	productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapProductResponse).toList();
	}

	private ProductResponse mapProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
