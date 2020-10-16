package com.khodko.studyproject.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	
	//TODO: add handling of different roles
	
	private final UserDao userDao;
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "login") String login, 
			@RequestParam(name = "password") String password,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
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
		Cookie cookie = new Cookie("currentUser", user.getLogin());
		cookie.setMaxAge(120);
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
