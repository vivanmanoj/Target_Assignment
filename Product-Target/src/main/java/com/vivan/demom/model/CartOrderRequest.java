package com.vivan.demom.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartOrderRequest {
	
	private final long cart_id;
	private final List<OrderedProduct> orderedProducts;

	  @Getter
	  @Builder
	  @AllArgsConstructor
	  public static class OrderedProduct {

	    private final long product_id;
	    private final int quantity;
	  }
}
