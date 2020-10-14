package com.khodko.studyproject.controllers;

import com.khodko.studyproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.dao.UserDao;

import java.util.List;

@Controller
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
//        User user = userDao.findByLogin("fredor");
//        return user.toString();
        List<User> users = userDao.findAll();
//        int size = users.size();
        return users.toString();
    }

    @GetMapping("/findByLogin")
    @ResponseBody
    public String findByLogin(){
        User user = userDao.findByLogin("pidr");
        System.out.println(user);
        return user.toString();//Throws exception if user is null
    }

}
