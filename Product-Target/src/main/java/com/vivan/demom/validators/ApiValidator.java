package com.vivan.demom.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.vivan.demom.model.Products;



@Component
public class ApiValidator {
	
	public boolean isValidProductId(int productId) {
		boolean validateFlag=true;
		
		
		return validateFlag;
	}

	public boolean isValidProductName(String productName) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(productName)) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isValidDepartmentName(String departmentName) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(departmentName)) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isValidComapny(String company) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(company)) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isValidTitle(String title) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(title)) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isValidIsbn(String isbn) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(isbn)) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isActive(String status) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(status) || !status.equalsIgnoreCase("true")) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	public boolean isValidPrice(long price) {
		boolean validateFlag=false;
		if(!StringUtils.isEmpty(price) || price <= 0) {
			validateFlag=true;
		}
		return validateFlag;
	}
	
	
	public boolean isValidProduct(Products product) {
		boolean validateFlag=false;
		if(null!=product && 
				isValidDepartmentName(product.getDepartment() )
				&& isValidComapny(product.getCompany())
				&& isValidTitle(product.getProduct_title()) 
				&& isValidIsbn(product.getIsbn())
				&& isActive(Boolean.toString(product.isActive()))
				/*&& isValidPrice(product.getCurrent_price())*/) {
			validateFlag=true;
		}
		return validateFlag;
	}

}
