package com.vivan.demom.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivan.demom.exceptions.ProductNotFoundException;
import com.vivan.demom.model.Cart;
import com.vivan.demom.model.CartOrderRequest;
import com.vivan.demom.model.CartOrderRequest.OrderedProduct;
import com.vivan.demom.model.Products;
import com.vivan.demom.model.SaleItem;
import com.vivan.demom.repository.ProductRepository;
import com.vivan.demom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Products> retriveProducts() {
		 List<Products> products = productRepository.findAll();
		  return products;
	}

	

	@Override
	public Products saveProduct(Products product) {
		 return productRepository.save(product);
		
	}
	/*public void saveProduct(Products product) {
		productRepository.save(product);
		
	}*/

	@Override
	public void deleteProduct(long prodId) {
		productRepository.deleteById(prodId);
		
	}

	@Override
	public void updateProduct(Products product) {
		productRepository.save(product);
		
	}

	@Override
	public Products getProduct(long prodId) {
		 Optional<Products> optEmp = productRepository.findById(prodId);
		 return optEmp.get();
	}

	@Override
	public Products getProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cart addItemsToCart(CartOrderRequest cartOrderRequest){
	    List<Products> products = cartOrderRequest.getOrderedProducts()
	    		.stream().map(orderedProduct-> productRepository.findById(orderedProduct.getProduct_id()).orElseThrow(()->new ProductNotFoundException("product not found for id")))
		        .collect(Collectors.toList());
	    

	    Map<Long, Integer> productMap = cartOrderRequest.getOrderedProducts().stream()
	        .collect(Collectors.toMap(OrderedProduct::getProduct_id, OrderedProduct::getQuantity));

	    List<SaleItem> saleItems = products.stream()
	        .map(product -> SaleItem.toSaleItem(product, productMap.get(product.getProduct_id())))
	        .collect(Collectors.toList());
	    saleItems.forEach(ProductServiceImpl::performRuleCheck);
	    Cart cart = Cart.toCart(saleItems);
	    performRuleCheckForCart(cart);
	    return cart;
	  }
	
	public static void performRuleCheck(SaleItem saleItem){
	    RuleManager.checkIfTenPlusQuantityOfSingleItem(saleItem);
	  }

	  public static void performRuleCheckForCart(Cart cart){
	    RuleManager.checkIfFivePlusItemsFromSameDept(cart);
	    RuleManager.checkIfTotalCostIsMoreThanFifty(cart);
	    RuleManager.checkForOneItem(cart);
	  }
	

}
