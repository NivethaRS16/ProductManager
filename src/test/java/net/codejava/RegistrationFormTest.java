package net.codejava;

import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import net.codejava.model.User;

public class RegistrationFormTest {
	
	private Validator validator;
	  @Before 
	  public void setUp() {
		  ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); 
		  validator = factory.getValidator(); 
	  }
	  
	  @Test 
	  public void testContactSuccess() 
	  { // I'd name the test to something like // invalidEmailShouldFailValidation()
	  
		  User user = new User(); 
		  user.setEmail("Nivi123@gmail.com");
		  user.setFirstName(null); 
		  Set<ConstraintViolation<User>> violations = validator.validate(user); 
		  assertFalse(violations.isEmpty()); 
      }
}
