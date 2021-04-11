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
public class ProdCrudTest {
	
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
	
	//Test Case 2 - Insert into DB - Unique Product Name Else throw error
	@Test
	@Rollback(false)
	public void testCreateProduct1() {
		Product savedProduct = repo.save(new Product("iPhone 10","Samsung1","China1", 989));
		System.out.println("Id is -- "+savedProduct.getId());
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
	
	//Test Case 3 - Search for data -
	@Test
	public void testFindProductByName() {
	    Product product = repo.findByName("iPhone 10");    
	    assertThat(product.getName()).isEqualTo("iPhone 10");
	}
	
	//Test Case 4 - Search for data -
	@Test
	public void testFindProductByName1() {
	    Product product = repo.findByName("iPhone 20");    
	    assertThat(product.getName()).isEqualTo("iPhone 20");
	}
	
	@Test
	public void testListProducts() {
	    List<Product> products = (List<Product>) repo.findAll();
	    assertThat(products).size().isGreaterThan(0);
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
	
	//Delete product
	@Test
	@Rollback(false)
	public void testDeleteProduct() {
	    Product product = repo.findByName("iPhone 10");
	     
	    repo.deleteById(product.getId());
	     
	    Product deletedProduct = repo.findByName("iPhone 10");
	     
	    assertThat(deletedProduct).isNull();       
	     
	}
}
