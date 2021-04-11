package net.codejava;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import net.codejava.model.Product;
import net.codejava.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductTest {
	
	@Autowired
	private ProductRepository repo;

	@Test
	@Rollback(false)
	public void testCreateProduct()
	{	
		Product product = new Product("Samsung S21 Ultra","Samsung","China",1399);
		Product savedProduct = repo.save(product);	
		assertNotNull(savedProduct);
	}
}
