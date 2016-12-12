package com.packt.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.*;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(String productId);

	List<Product> getProductByCategory(String category);

	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	List<Product> getProductByManufacturer(String manufacturer);

	Set<Product> getProductByPriceFilter(Map<String,  List<String>> filterParams, String manufacturer, String category);

	void addProduct(Product product);
}
