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
public class AdditionController {
	
	//TODO: Resolve access issue with get mapping for /add
	
	private final UserDao userDao;
	private final RoleDao roleDao;
	
	@GetMapping("/add")
	public String redirect(Model model) {
		model.addAttribute("action", "Add");
		return "user_data";
	}
	
	@PostMapping("/add")
	public String addUser(@ModelAttribute User user, @RequestParam(name = "role") String roleName,
			HttpSession session, Model model) {
		
		//Check whether user with provided login already exists:
		if(userDao.findByLogin(user.getLogin()) != null) {
			model.addAttribute("msg", "User with specified login already exists");
			model.addAttribute("action", "Add");
			return "user_data";
		}
		
		user.setRole(roleDao.findByName(roleName));
		userDao.create(user);
		
		//Add user to session:
		((List<User>)session.getAttribute("users")).add(user);
	
		return "admin_home";
	}
}
