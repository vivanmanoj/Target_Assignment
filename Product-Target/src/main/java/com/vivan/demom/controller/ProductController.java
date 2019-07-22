package com.vivan.demom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vivan.demom.dto.APIResponse;
import com.vivan.demom.model.Products;
import com.vivan.demom.repository.ProductRepository;
import com.vivan.demom.service.ProductService;
import com.vivan.demom.validators.ApiValidator;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ApiValidator apivalidator;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	 @GetMapping("/products")
	 public List<Products> getEmployees() {
	  List<Products> products = productService.retriveProducts();
	  return products;
	 }
	 
	 @GetMapping("/products/{department}")
	 public List<Products> getEmployees(@PathVariable(name="department")String department) {
	  List<Products> products = productRepository.findByDepartment(department);
	  return products;
	 }
	 
	 @GetMapping("/product/{company}")
	 public List<Products> getEmployeesCompany(@PathVariable(name="company")String company) {
	  List<Products> products = productRepository.findByCompany(company);
	  return products;
	 }
	 
	 @GetMapping("/product/cmp/{product_title}")
	 public List<Products> getEmployeesTitle(@PathVariable(name="product_title")String product_title) {
	  List<Products> products = productRepository.findByProduct_Title(product_title);
	  return products;
	 }
	
	 @PostMapping("/products")
	 public ResponseEntity<APIResponse> saveEmployee(@RequestBody Products product){
		 boolean isValidInput=apivalidator.isValidProduct(product);
		 
		 APIResponse apiResponse=new APIResponse();
		 try {
		 if(isValidInput) {
			 	productService.saveProduct(product);
			 	apiResponse.setStatus("000");
		 		apiResponse.setMessage("Successfully created product "+product.getProduct_id());
		 		System.out.println("Product saved Successfully");
	    	}else {
	    		apiResponse.setStatus("400");
	    		apiResponse.setMessage("Please specify the product details");
	    		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	    	}
		 
		 } catch(Exception ex) {
		    	apiResponse.setStatus("404");
				apiResponse.setMessage(ex.getMessage());
		    	return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
		    }
		 
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/products/{id}")
	 public ResponseEntity<APIResponse> deleteEmployee(@PathVariable(name="id")long productId){
		 APIResponse apiResponse=new APIResponse();
		 try {
		 productService.deleteProduct(productId);
		 apiResponse.setStatus("000");
	 	 apiResponse.setMessage("Successfully Deleted  "+productId);
	 		System.out.println("Product saved Successfully");
		 } catch(Exception ex) {
		    apiResponse.setStatus("404");
			apiResponse.setMessage(ex.getMessage());
		    return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
		    }
		 return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
	 }
	 
	 @PutMapping("/products/{id}")
	 public ResponseEntity<APIResponse> updateEmployee(@RequestBody Products products,
	   @PathVariable(name="id")Long productId){
		 APIResponse apiResponse=new APIResponse();
		try {
		 Products prod = productService.getProduct(productId);
		 if(prod != null){
			 products.setProduct_id(productId);
			 productService.updateProduct(products);
			 apiResponse.setStatus("000");
		 	 apiResponse.setMessage("Successfully updated  "+productId);
		 }else {
			 apiResponse.setStatus("405");
			 apiResponse.setMessage("Mentioned ProductId   "+productId+" Not Found");
			 return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
		 }
		 } catch(Exception ex) {
			 	apiResponse.setStatus("404");
				apiResponse.setMessage(ex.getMessage());
			    return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);
	 }

}
