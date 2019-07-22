package com.vivan.demom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Entity
public class Discounts {

	  @Id
	  @GeneratedValue
	  private final long discount_id;
	 // private final DiscountType discountType;
	 // private final int discountValue;
	 // private final String discountReason;

	  public enum DiscountType {
	  FLAT, PERCENTAGE;
	  }
}
