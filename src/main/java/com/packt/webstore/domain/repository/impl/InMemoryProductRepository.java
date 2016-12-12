package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	private ArrayList<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {

		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);

		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);

		Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_Nexus.setDescription(
				"Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Pro processor");
		tablet_Nexus.setCategory("Tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);

		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);
	}

	public List<Product> getAllProduct() {
		return listOfProducts;
	}

	public Product getProductById(String productId) {
		Product productById = null;
		for (Product product : listOfProducts) {
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}

		if (productById == null) {
			throw new ProductNotFoundException("No products found with the product id:" + productId);
		}

		return productById;
	}

	public List<Product> getProductByCategory(String category) {
		List<Product> productByCategory = new ArrayList<Product>();
		for (Product product : listOfProducts) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productByCategory.add(product);
			}
		}
		return productByCategory;
	}

	public Set<Product> getProductByFilter(Map<String, List<String>> filterParams) {

		Set<Product> productsByBrands = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();

		if (criterias.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				for (Product product : listOfProducts) {
					if (brandName.equalsIgnoreCase(product.getManufacturer()))
						;
					productsByBrands.add(product);
				}
			}
		}
		if (criterias.contains("category")) {
			for (String categoryName : filterParams.get("category")) {
				productsByCategory.addAll(this.getProductByCategory(categoryName));

			}
		}
		productsByCategory.retainAll(productsByBrands);
		return productsByCategory;
	}

	public List<Product> getProductByManufacturer(String manufacturer) {
		ArrayList<Product> productByManufacturer = new ArrayList<Product>();
		for (Product product : listOfProducts) {
			if (product != null && product.getManufacturer() != null && product.equals(manufacturer))
				;
			productByManufacturer.add(product);
		}
		return productByManufacturer;
	}

	public Set<Product> getProductByPriceFilter(Map<String, List<String>> filterParams, String manufacturer,
			String category) {

		Set<Product> productsByManufacturer = new HashSet<Product>();// Reqest
																		// param
		Set<Product> productsByPrice = new HashSet<Product>();// Matrix
		Set<Product> productsByCategory = new HashSet<Product>();// template URL

		Set<String> criterias = filterParams.keySet();
		BigDecimal lowPrice = null;
		BigDecimal highPrice = null;
		if (criterias.contains("low") && criterias.contains("high")) {
			for (String priceName : filterParams.get("low")) {
				lowPrice = BigDecimal.valueOf(Integer.parseInt(priceName));
			}
			for (String priceName : filterParams.get("high")) {
				highPrice = BigDecimal.valueOf(Integer.parseInt(priceName));
			}

			for (Product product : listOfProducts) {
				if (product != null && product.getUnitPrice() != null
						&& (product.getUnitPrice().compareTo(lowPrice) == 1
								&& product.getUnitPrice().compareTo(highPrice) == -1)) {
					productsByPrice.add(product);
				}
			}

		} else {

			if (criterias.contains("low")) {
				for (String priceName : filterParams.get("low")) {
					lowPrice = BigDecimal.valueOf(Integer.parseInt(priceName));
				}
				for (Product product : listOfProducts) {
					if (product != null && product.getUnitPrice() != null
							&& product.getUnitPrice().compareTo(lowPrice) == 1) {
						productsByPrice.add(product);
					}
				}
			}
			if (criterias.contains("high")) {
				for (String priceName : filterParams.get("high")) {
					lowPrice = BigDecimal.valueOf(Integer.parseInt(priceName));
				}
				for (Product product : listOfProducts) {
					if (product != null && product.getUnitPrice() != null
							&& product.getUnitPrice().compareTo(lowPrice) == -1) {
						productsByPrice.add(product);
					}
				}
			}
		}

		productsByManufacturer.addAll(this.getProductByManufacturer(manufacturer));
		productsByPrice.retainAll(productsByManufacturer);

		productsByCategory.addAll(this.getProductByCategory(category));
		productsByPrice.retainAll(productsByCategory);

		return productsByPrice;
	}

	public void addProduct(Product product) {
		listOfProducts.add(product);
	}

}
