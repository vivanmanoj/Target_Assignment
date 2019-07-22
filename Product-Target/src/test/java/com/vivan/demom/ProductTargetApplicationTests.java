package com.vivan.demom;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivan.demom.model.Products;
import com.vivan.demom.repository.ProductRepository;
import com.vivan.demom.service.ProductService;
import com.vivan.demom.service.impl.ProductServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTargetApplicationTests {
	
	 ObjectMapper Obj = new ObjectMapper(); 
	//@Autowired
	//private MockMvc mockMvc;
	
	//@MockBean
	//ProductService productService;
	@InjectMocks
	ProductServiceImpl ProductServiceImpl;
	
	@Mock
	ProductRepository productRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void saveProduct(){
		List<String> l_p = new ArrayList<String>();
		l_p.add("10001");
		l_p.add("10002");
		Products prod = new Products(1l,"Home",300l,"Samsung","LED","TV","desc","6/9/2014","3/14/2029","45555","Black",true,l_p);
		when(productRepository.save(prod)).thenReturn(prod);
		Products result = ProductServiceImpl.saveProduct(prod);
		assertEquals(1l, result.getProduct_id());
	}
	
	
	@Test
	public void removeProduct(){
		List<String> l_p = new ArrayList<String>();
		l_p.add("10001");
		l_p.add("10002");
		Products prod = new Products(2l,"Home",300l,"Samsung","LED","TV","desc","6/9/2014","3/14/2029","45555","Black",true,l_p);
		ProductServiceImpl.deleteProduct(prod.getProduct_id());
        verify(productRepository, times(1)).deleteById(prod.getProduct_id());
	}
	
	
	@Test
	public void findAllProduct(){
		List<String> l_p = new ArrayList<String>();
		l_p.add("10005");
		l_p.add("10006");
		List<String> l_p1 = new ArrayList<String>();
		l_p1.add("10009");
		l_p1.add("10010");
		
		Products prod = new Products(3l,"Mobile",300l,"Samsung","SmartPhone","GalaxyS10","desc","6/9/2014","3/14/2029","45555","Black",true,l_p);
		Products prod1 = new Products(4l,"TV",400l,"Micromax","LED","TV","desc","6/9/2014","3/14/2029","45557","Silver",true,l_p1);
		List<Products> prodList = new ArrayList<Products>();
		prodList.add(prod);
		prodList.add(prod1);
		
		when(productRepository.findAll()).thenReturn(prodList);
		
		List<Products> result = ProductServiceImpl.retriveProducts();
		assertEquals(2, result.size());
		
		
		
		
	}
	
	
	

}
