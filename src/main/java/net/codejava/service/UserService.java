package net.codejava.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.codejava.model.User;
import net.codejava.web.dto.UserRegistrationDto;


public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
