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
public class ProductSearchTest {
	
	@Autowired
	private ProductRepository repo;

	//Test Case 1 - Insert into DB 
	@Test
	@Rollback(false)
	public void testCreateProduct() {
		Product savedProduct = repo.save(new Product("iPhone 101","Samsung","China", 789));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	//Test Case 2 - Search for data -prsent
	@Test
	public void testFindProductByName() {
	    Product product = repo.findByName("iPhone 101");    
	    assertThat(product.getName()).isEqualTo("iPhone 101");
	}
	
	//Test Case 3 - Search for data - not present
	@Test
	public void testFindProductByName1() {
	    Product product = repo.findByName("iPhone 201");    
	    assertThat(product.getName()).isEqualTo("iPhone 201");
	}

	
}
