package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.getAllProduct();
	}

	public Product getProductById(String productId) {
		return productRepository.getProductById(productId);
	}

	public List<Product> getProductByCategory(String category) {

		return productRepository.getProductByCategory(category);
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductByFilter(filterParams);
	}

	public List<Product> getProductByManufacturer(String manufacturer) {
		return productRepository.getProductByManufacturer(manufacturer);
	}

	public Set<Product> getProductByPriceFilter(Map<String,  List<String>> filterParams, String manufacturer,
			String category) {
		return productRepository.getProductByPriceFilter(filterParams, manufacturer, category);
	}

	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
