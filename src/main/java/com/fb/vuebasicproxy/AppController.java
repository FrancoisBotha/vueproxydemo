package com.fb.vuebasicproxy;

import java.util.Map;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/app")
public class AppController  {

	@GetMapping
	public ModelAndView ShowApp(ModelMap model) {
		
		Object accessToken;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			Map<String, Object> i =  (Map<String, Object>) auth.getDetails();
			accessToken = i.get("accessToken");
		        // userDetails = auth.getPrincipal()
		} else {
			accessToken = "Not Defined";
		}
		
		return new ModelAndView("spa", "message", "Access Token:" + accessToken.toString());
	}

}