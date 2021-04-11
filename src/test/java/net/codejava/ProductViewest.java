package net.codejava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import net.codejava.model.Product;
import net.codejava.repository.ProductRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductViewest {

	@Autowired
	private ProductRepository repo;
	
	@Test
	@Rollback(false)
	public void testCreateProduct1() {
		Product savedProduct = repo.save(new Product("iPhone 101","Samsung","China", 789));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	

	@Test
	public void testListProducts() {
	    List<Product> products = (List<Product>) repo.findAll();
	    assertThat(products).size().isGreaterThan(0);
	}
	
	//Delete product
	@Test
	public void testDeleteProduct() {
	    Product product = repo.findByName("iPhone 101");
	     
	    repo.deleteById(product.getId());
	     
	    Product deletedProduct = repo.findByName("iPhone 101");
	     
	    assertThat(deletedProduct).isNull();       
	     
	}

	@Test
	public void testListProducts1() {
	    List<Product> products = (List<Product>) repo.findAll();
	    assertThat(((Product) products).getId()).isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testCreateProduct2() {
		Product savedProduct = repo.save(new Product("iPhone 10","Samsung1","China1", 989));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	//Update Product
	@Test
	@Rollback(false)
	public void testUpdateProduct() {
	    Product product = repo.findByName("iPhone 10");
	    product.setPrice(1000);
	    repo.save(product);
	    Product updatedProduct = repo.findByName("iPhone 10");
	    assertThat(updatedProduct.getPrice()).isEqualTo(1000);
	}
}
