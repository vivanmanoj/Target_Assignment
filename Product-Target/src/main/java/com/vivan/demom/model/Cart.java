package com.vivan.demom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Cart {
	private final String cart_id;
	@Setter
	private long cart_overall_cost;
	private List<String> discounts;
	private final List<SaleItem> saleItems;
	
	public static Cart toCart(List<SaleItem> saleItems){
		   return Cart.builder()
		        .cart_id(UUID.randomUUID().toString())
		        .saleItems(saleItems)
		        .discounts(updateDiscountsApplied(saleItems))
		        .cart_overall_cost(saleItems.stream().collect(Collectors.summingLong(saleItem -> saleItem.getFinal_selling_price())))
		        .build();
		  }

		  public static List<String> updateDiscountsApplied(List<SaleItem> saleItems){
		    List<String> discountList = new ArrayList<>();
		    saleItems.stream().forEach(saleItem -> {
		      if(saleItem.getDiscountApplied()!=null){
		        discountList.add(saleItem.getDiscountApplied());
		      }
		    });
		    return discountList;
		  }

		  public void setDiscount(String discount){
		    discounts = discounts==null?new ArrayList<>():this.discounts;
		    discounts.add(discount);
		  }

}
