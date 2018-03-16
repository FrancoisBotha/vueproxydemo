package com.fb.vuebasicproxy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String ShowHomePage(ModelMap model) {
		
		log.info("InShowHomePage Controller");
		return "home";
	}

}