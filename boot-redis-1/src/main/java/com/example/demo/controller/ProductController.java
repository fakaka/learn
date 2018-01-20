package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductMapper productMapper;

	@GetMapping("/{id}")
	public Product getProductInfo(@PathVariable("id") Long productId) {
		return productMapper.select(productId);
	}

	@PutMapping("/{id}")
	public Product updateProductInfo(@PathVariable("id") Long productId, @RequestBody Product newProduct) {
		Product product = productMapper.select(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		}
		product.setName(newProduct.getName());
		product.setPrice(newProduct.getPrice());
		productMapper.update(product);
		return product;
	}
}
