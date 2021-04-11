package net.codejava.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.model.User;
import net.codejava.service.UserService;
import net.codejava.web.dto.UserRegistrationDto;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@Valid @ModelAttribute("user")  UserRegistrationDto registrationDto,BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Validation errors while submitting form.");
			System.out.println(registrationDto.toString());
			return "registration";
		}
		else
		{
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}
		
	}

	
}
