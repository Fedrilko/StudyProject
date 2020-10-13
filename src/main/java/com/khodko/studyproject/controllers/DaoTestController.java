package com.khodko.studyproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.dao.UserDao;

//@Controller
public class DaoTestController {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/testRoleDao")
	@ResponseBody
	public String testRoleDao() {
		return roleDao.findByName("Admin").toString();
	}
	
	@GetMapping("/testUserDao")
	@ResponseBody
	public String testUserDao() {
		return roleDao.findByName("Admin").toString();
	}
}
