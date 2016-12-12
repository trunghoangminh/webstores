package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.packt.webstore.domain.*;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepository;

	public void processOrder(String productId, int count) {

		Product productById = productRepository.getProductById(productId);

		if (productById.getUnitsInStock() < count) {

			throw new IllegalArgumentException(
					"Out of stock. Available Units in stock" + productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock() - count);
	}

}
