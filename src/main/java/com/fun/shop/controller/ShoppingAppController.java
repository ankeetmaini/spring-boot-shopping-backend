package com.fun.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fun.shop.domain.Product;
import com.fun.shop.repository.ProductRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ShoppingAppController {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppController.class, args);
	}
}


@RestController
class ProductService {
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		productRepository.saveAll(List.of(
			new Product("1", "potato"),
			new Product("2", "onions"),
			new Product("3", "chips"),
			new Product("4", "fruits")
		))
		;
	}

	@GetMapping(value = "/products")
	Iterable<Product> getProducts() {
		return productRepository.findAll();
	}

	@GetMapping(value = "/products/{id}")
	Optional<Product> getProductById(@PathVariable String id) {
		return productRepository.findById(id);
	}
}
