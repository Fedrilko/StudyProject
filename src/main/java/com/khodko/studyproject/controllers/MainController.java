package com.khodko.studyproject.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

	//TODO: cookies support, resolve script issue on views, sending messages on view
	
	private final UserDao userDao;
	
	@RequestMapping({ "/main", "/" })
	public String main(HttpSession session, Authentication authentication, Model model) {
		
		if (authentication == null) {
			return "login";
		}
		
		User user = (User) authentication.getPrincipal();
		if(user.getRole().getName().equals("Admin")) {
			List<User> users = userDao.findAll();
			session.setAttribute("users", users);
		}
		session.setAttribute("currentUser", user);
		return getViewName(user);
	}

	private String getViewName(User user) {
		if (user.getRole().getName().equals("User")) {
			return "user_home";
		} else
			return "admin_home";
	}
	
//Uncomment following to use custom auth functionality (non-spring security):
//	@RequestMapping({ "/main", "/" })
//	public String main(HttpSession session, HttpServletRequest request) {
//		// If user exists in session:
//		User user = (User) session.getAttribute("currentUser");
//		if (user != null) {
//			return getViewName(user);
//		}
//		// Try to find cookie:
//		if (request.getCookies() != null) {
//			for (Cookie cookie : request.getCookies()) {
//				if (cookie.getName().equals("currentUser")) {
//					user = userDao.findByLogin(cookie.getValue());
//					List<User> users = userDao.findAll();
//					session.setAttribute("currentUser", user);
//					session.setAttribute("users", users);
//					return getViewName(user);
//				}
//			}
//		}
//		return "login";
//	}
}


