package com.vivan.demom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivan.demom.model.Products;

public interface DiscountsRepository extends JpaRepository<Products, String> {

}
