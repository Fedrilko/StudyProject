package com.khodko.studyproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	private final UserDao userDao;
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "login") String login, 
			@RequestParam(name = "password") String password,
			HttpSession session, HttpServletRequest request) {
		User user = userDao.findByLogin(login);
		if(user == null) {
			request.setAttribute("msg", "User does not exist");
			return "login";
		}
		if(!password.equals(user.getPassword())) {
			request.setAttribute("msg", "Wrong password");
			return "login";
		}
		session.setAttribute("currentUser", user);
		if(user.getRole().getName().equals("User")) {
			return "user_home";
		} else return "admin_home";
			
	}

}
