package com.vivan.demom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivan.demom.model.Cart;
import com.vivan.demom.model.CartOrderRequest;
import com.vivan.demom.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {
	 @Autowired
	  ProductService service;

	    @PostMapping
	    public Cart addItems(@RequestBody CartOrderRequest cartOrderRequest) {
	        return service.addItemsToCart(cartOrderRequest);
	    }
}
