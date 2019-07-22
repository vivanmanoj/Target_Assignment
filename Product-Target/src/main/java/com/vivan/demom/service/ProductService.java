package com.vivan.demom.service;

import java.util.List;

import com.vivan.demom.model.Cart;
import com.vivan.demom.model.CartOrderRequest;
import com.vivan.demom.model.Products;

public interface ProductService {
	public List<Products> retriveProducts();
	public Products getProduct();
	//public void saveProduct(Products product);
	public Products saveProduct(Products product);
	public void deleteProduct(long prodId);
	public void updateProduct(Products product);
	public Products getProduct(long prodId);
	public Cart addItemsToCart(CartOrderRequest cartOrderRequest);
	//public static void performRuleCheck(SaleItem saleItem);

}
