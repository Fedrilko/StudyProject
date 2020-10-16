package com.khodko.studyproject.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		for(Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("currentUser")) {
				cookie.setMaxAge(0); //remove cookie
				response.addCookie(cookie);
			}
		}
		return "login";
	}
}
