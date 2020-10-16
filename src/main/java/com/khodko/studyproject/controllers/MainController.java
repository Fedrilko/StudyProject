package com.khodko.studyproject.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

	private final UserDao userDao;

	@RequestMapping({ "/main", "/" })
	public String main(HttpSession session, HttpServletRequest request) {
		// If user exists in session:
		User user = (User) session.getAttribute("currentUser");
		if (user != null) {
			return getViewName(user);
		}
		// Try to find cookie:
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("currentUser")) {
					user = userDao.findByLogin(cookie.getValue());
					session.setAttribute("currentUser", user);
					return getViewName(user);
				}
			}
		}

		return "login";
	}

	private String getViewName(User user) {
		if (user.getRole().getName().equals("User")) {
			return "user_home";
		} else
			return "admin_home";
	}
}
