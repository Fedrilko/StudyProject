package com.khodko.studyproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RemovalController {
	
	@GetMapping("/remove")
	public String removeUser() {
		return null;
	}

}
