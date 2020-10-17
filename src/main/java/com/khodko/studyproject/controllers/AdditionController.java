package com.khodko.studyproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdditionController {
	
	private UserDao userDao;
	
	public String addUser(@ModelAttribute User user, HttpSession session) {
		return null;
	}
}
