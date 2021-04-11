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
public class ProdCreateTest {
	
	@Autowired
	private ProductRepository repo;

	//Test Case 1 - Insert into DB 
	@Test
	@Rollback(false)
	public void testCreateProduct1() {
		Product savedProduct = repo.save(new Product("iPhone 101","Samsung","China", 789));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	//Test Case 2 - Insert into DB - Unique Product Name Else throw error
	@Test
	@Rollback(false)
	public void testCreateProduct2() {
		Product savedProduct = repo.save(new Product("iPhone 101","Samsung1","China1", 989));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	//Test Case 3 - Insert into DB - Unique Product Name Else throw error
		@Test
		@Rollback(false)
		public void testCreateProduct3() {
			Product savedProduct = repo.save(new Product("iPhone 201","Samsung1","China1", 989));
			System.out.println("Id is -- "+savedProduct.getId());
			assertThat(savedProduct.getId()).isGreaterThan(0);
		}
	
	//Test Case 4 - Insert into DB - Unique Product Name Else throw error
		@Test
		@Rollback(false)
		public void testCreateProduct4() {
			Product savedProduct = repo.save(new Product());
			System.out.println("Id is -- "+savedProduct.getId());
			assertThat(savedProduct.getId()).isGreaterThan(0);
		}
}
