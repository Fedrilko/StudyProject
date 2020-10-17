package com.khodko.studyproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdditionController {
	
	private UserDao userDao;
	
	@GetMapping("/add")
	public String redirect(HttpServletRequest request) {
		request.setAttribute("action", "Add");
		return "user_data";
	}
	
	
	@PostMapping("/add")
	public String addUser(@ModelAttribute User user, HttpSession session, Model model) {
		return null;
	}
}
