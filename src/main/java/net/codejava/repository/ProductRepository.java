package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import net.codejava.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	Product findByName(String name);
 
}