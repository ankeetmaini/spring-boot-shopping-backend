package com.fun.shop.repository;

import com.fun.shop.domain.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String>{
  
}
