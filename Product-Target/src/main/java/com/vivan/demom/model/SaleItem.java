package com.vivan.demom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class SaleItem {
	private final long product_id;
	  private String department;
	  private final int quantity;
	 // private long base_price;
	  private double base_price;
	  @Setter
	  private double price_with_quantity_multiplied;
	 // private long price_with_quantity_multiplied;
	  @Setter
	  private double final_selling_price;
	 // private long final_selling_price;
	  @Setter
	  private String discountApplied;

	  public static SaleItem toSaleItem(Products product, int quantity){
	    return SaleItem.builder()
	        .product_id(product.getProduct_id())
	        .base_price(product.getCurrent_price())
	        .department(product.getDepartment())
	        .quantity(quantity)
	        .price_with_quantity_multiplied(product.getCurrent_price() * quantity)
	        .final_selling_price(product.getCurrent_price() * quantity)
	        .build();
	  }

	  public void setBase_price(long val){
	    this.base_price = val;
	    updateDependentValues();
	  }

	  private void updateDependentValues() {
	    this.price_with_quantity_multiplied = this.base_price * this.quantity;
	    this.final_selling_price = this.price_with_quantity_multiplied;
	  }

}
