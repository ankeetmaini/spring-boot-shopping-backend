package com.fun.shop.controller;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
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
class ConfigParamsController {
  @Value("${environment}")
  private String environment;

  @GetMapping("/environment")
  String getEnvironment() {
    return environment;
  }
}


@RestController
class ProductService {
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository repository) {
		this.productRepository = repository;
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


@Entity
class Product {
  
  @Id
	private String id;
	private String name;
	
	public Product(String id, String name) {
		this.id = id;
		this.name = name;
	}

  public Product() {}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

interface ProductRepository extends CrudRepository<Product, String>{
}


@Component
class DataDump {
  private ProductRepository productRepository;

  public DataDump(ProductRepository repository) {
    this.productRepository = repository;
  }

  @PostConstruct
  public void dump() {
    productRepository.saveAll(List.of(
			new Product("1", "potato"),
			new Product("2", "onions"),
			new Product("3", "chips"),
			new Product("4", "fruits")
		));
  }
}
