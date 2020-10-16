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

@Controller
public class LoginController {
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "login") String login, 
			@RequestParam(name = "password") String password,
			Model model, HttpSession session) {
		User user = userDao.findByLogin(login);
		if(user == null) {
//			model.addAttribute("msg", "User does not exist");
			session.setAttribute("msg", "User does not exist");//bad idea to put this into session
//			return "redirect:/login";
			return "login";
		}
		if(!password.equals(user.getPassword())) {
//			model.addAttribute("msg", "Wrong password");
			session.setAttribute("msg", "Wrong password");//bad idea to put this into session
//			return "redirect:/login";
			return "login";
		}
		model.addAttribute("currentUser", user);
		session.setAttribute("currentUser", user);
		if(user.getRole().getName().equals("User")) {
			return "user_home";
		} else return "admin_home";
			
	}

}
