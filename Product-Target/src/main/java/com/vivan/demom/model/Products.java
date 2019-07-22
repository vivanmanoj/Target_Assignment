package com.vivan.demom.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vivan.demom.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PRODUCTS")
@Setter
public class Products {
	  @Id
	  @GeneratedValue
	  private long product_id;
	  private String department;
	 // private long current_price;
	  private double current_price;
	  private String company;
	  private String product_title;
	  private String product_subtitle;
	  private String product_description;
	  private String lifecycle_start;
	  private String lifecycle_end;
	  private String isbn;
	  private String color;
	  private boolean active;
	  @ElementCollection(targetClass=String.class)
	  private List<String> like_products;

	  public Timestamp getLifecycle_start() throws ParseException {
	    Date date = new SimpleDateFormat(Constants.lifeCycleDateFormat).parse(lifecycle_start);
	    return new Timestamp(date.getTime());
	  }

	  public Timestamp getLifecycle_end() throws ParseException {
	    Date date = new SimpleDateFormat(Constants.lifeCycleDateFormat).parse(lifecycle_end);
	    return new Timestamp(date.getTime());
	  }

}
