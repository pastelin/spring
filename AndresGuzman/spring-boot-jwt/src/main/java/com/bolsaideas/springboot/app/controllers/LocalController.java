package com.bolsaideas.springboot.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalController {

	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		// referer obtiene la referencia de la ultima url
		String ultimaUrl = request.getHeader("referer");
		return "redirect:".concat(ultimaUrl);
	}
	
}
