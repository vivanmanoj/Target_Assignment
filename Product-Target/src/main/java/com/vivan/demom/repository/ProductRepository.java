package com.vivan.demom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vivan.demom.model.Products;
@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	public List<Products> findByDepartment(String department);
	public List<Products> findByCompany(String company);
	@Query("from Products where product_title=?1")
	public List<Products> findByProduct_Title(String product_title);
	
}
