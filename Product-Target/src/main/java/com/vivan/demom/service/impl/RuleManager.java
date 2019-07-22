package com.vivan.demom.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vivan.demom.model.Cart;
import com.vivan.demom.model.SaleItem;

public class RuleManager {
	//cartLevel rule
	  public static String checkIfFivePlusItemsFromSameDept(Cart cart) {
	    List<SaleItem> saleItems = cart.getSaleItems();
	    Map<String, Integer> deptToCountMap = saleItems.stream()
	        .collect(Collectors
	            .groupingBy(SaleItem::getDepartment, Collectors.summingInt(SaleItem::getQuantity)));
	    Optional<Integer> deptCountOptional = deptToCountMap.values().stream().filter(s -> s > 5)
	        .findFirst();
	    deptCountOptional.ifPresent(__ -> {
	      cart.setCart_overall_cost((long) (cart.getCart_overall_cost() * 0.90));
	    });
	    if(deptCountOptional.isPresent()) {
	      cart.setDiscount("Five_Items_Same_Department_Reduce_overall_cost_10_percent");
	    }
	    return null;
	  }

	  //cartLevel rule
	  public static String checkIfTotalCostIsMoreThanFifty(Cart cart) {
	    if (cart.getCart_overall_cost() > 50) {
	      cart.setCart_overall_cost(cart.getCart_overall_cost() - 5);
	      cart.setDiscount("Total_Cost_Greater_Than_50");
	    }
	    return null;
	  }

	  //cartLevel rule
	  public static String checkForOneItem(Cart cart) {
	    if (cart.getSaleItems().stream().collect(Collectors.summingInt(SaleItem::getQuantity)) == 1) {
	      cart.setCart_overall_cost((long) (cart.getCart_overall_cost() * 0.50));
	      cart.setDiscount("Check for one item Discount Applied");
	    }
	    return null;
	  }

	//itemLevel rule
	  public static void checkIfTenPlusQuantityOfSingleItem(SaleItem saleItem) {
	    if(saleItem.getQuantity() > 10){
	      saleItem.setBase_price((long) (saleItem.getBase_price() * 0.90));
	      saleItem.setDiscountApplied("Ten Plus Quantity Of Single Item Discount Applied");
	    }
	  }

}
