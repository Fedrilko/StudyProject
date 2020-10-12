package com.khodko.studyproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khodko.studyproject.dao.RoleDao;

//@Controller
public class DaoTestController {
	
	@Autowired
	private RoleDao dao;
	
	@GetMapping("/testDao")
	@ResponseBody
	public String testDao() {
		return dao.findByName("Admin").toString();
	}
}
