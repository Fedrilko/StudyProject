package com.khodko.studyproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RemovalController {
	
	//TODO: Admin is not able to remove himself from the list of users. Figure out how to handle this sh*t
	
	private UserDao userDao;
	
	@GetMapping("/remove")
	public String removeUser(@RequestParam(name = "userId") long userId,
			HttpSession session) {
		List<User> users = (List<User>)session.getAttribute("users");
		User userToDelete = users.stream().filter(u -> u.getId() == userId).findFirst().get();
		users.remove(userToDelete); //remove from list in session
		userDao.remove(userToDelete);
		return "admin_home";
	}

}
