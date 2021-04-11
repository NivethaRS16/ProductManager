package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import net.codejava.model.User;
import net.codejava.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entitymanager;
	
	@Test
	public void testUser()
	{
		User user = new User();
		user.setEmail("nharsi2001@gmail.com");
		user.setFirstName("Harshitha");
		user.setLastName("Senthilkumar");
		user.setPassword("harshi");
		
		User savedUser = repo.save(user);
		User existUser = entitymanager.find(User.class,savedUser.getId());
		try
		{
		//existUser.setEmail("Dummy@gmail.com");	
		assertThat(savedUser.getEmail()).isEqualTo(existUser.getEmail());
		}
		catch(AssertionError e)
		{
			assertThat(e).hasMessage("There is a difference in the Email found and test case failed");
		}
	}
	
//	@Test
//	public void findUserByEmail()
//	{
//		String email = "nharsi2000@gmail.com";
//		User user = repo.findByEmail(email);
//		assertThat(user).isNotNull();
//	}

}
