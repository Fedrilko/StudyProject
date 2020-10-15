package com.khodko.studyproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khodko.studyproject.models.User;

@Controller
public class MainController {
	@RequestMapping({"/main", "/"})
	public String main(HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		if(user != null) {
			if(user.getRole().getName().equals("User")) {
				return "user_home";
			} else return "admin_home";
		}
		return "login";
	}
}
