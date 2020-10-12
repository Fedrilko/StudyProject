package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

import java.util.List;

public class HibernateUserDao implements UserDao {
    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
