package com.khodko.studyproject.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {
	@GetMapping("/update")
	public String redirect(@RequestParam(name = "userId") long id, HttpServletRequest request, Model model) {
		System.out.println("Inside of update controller");
		request.setAttribute("action", "Edit");
		return "user_data";
	}
}
