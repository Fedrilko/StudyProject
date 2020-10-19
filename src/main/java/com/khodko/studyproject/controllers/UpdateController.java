package com.khodko.studyproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UpdateController {
	
	private final UserDao userDao;
	private final RoleDao roleDao;
	
	@GetMapping("/edit")
	public String redirect(@RequestParam(name = "userLogin") String login, Model model) {
		model.addAttribute("user", userDao.findByLogin(login));
		model.addAttribute("action", "Edit");
		return "user_data";
	}
	
	@PostMapping("/edit")
	public String addUser(@ModelAttribute User user, @RequestParam(name = "role") String roleName,
			HttpSession session, Model model) {
		user.setId(userDao.findByLogin(user.getLogin()).getId());
		user.setRole(roleDao.findByName(roleName));
		
		userDao.update(user);
		
		//Update users in session:
		session.setAttribute("users", userDao.findAll());
	
		return "admin_home";
	}
}
