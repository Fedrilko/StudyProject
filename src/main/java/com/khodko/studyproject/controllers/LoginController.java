package com.khodko.studyproject.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	private final UserDao userDao;

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}


	@PostMapping("/login")
	public String login(@RequestParam(name = "login") String login, 
			@RequestParam(name = "password") String password, Model model,
			HttpSession session, HttpServletResponse response) {
		System.out.println("----------Inside of login controller----------"); //debug line
		User user = userDao.findByLogin(login);
		
		if(user == null) {
			model.addAttribute("msg", "User does not exist");
			return "login";
		}
		if(!password.equals(user.getPassword())) {
			model.addAttribute("msg", "Wrong password");
			return "login";
		}
		
		//Put user into session
		session.setAttribute("currentUser", user);
		
		//Create cookie
		Cookie cookie = new Cookie("currentUser", user.getLogin());
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		
		if(user.getRole().getName().equals("User")) {
			return "user_home";
		} else {
			List<User> users = userDao.findAll();
			session.setAttribute("users", users);
			return "admin_home";
		}
			
	}

}
