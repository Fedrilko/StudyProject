package com.khodko.studyproject.dao;

import com.khodko.studyproject.models.Role;

public interface RoleDao {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);
}
