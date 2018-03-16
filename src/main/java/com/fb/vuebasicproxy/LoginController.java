package com.fb.vuebasicproxy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping({"/login"})
public class LoginController {

	@GetMapping
	public String ShowLoginForm() {
		log.debug("InShowLoginForm Controller");
		return "login";
	}
	
	@PostMapping
	public String ShowVuePage(Model model) {
		
		return "spa";
	}
	
}