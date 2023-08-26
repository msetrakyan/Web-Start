package com.smartcode.web.service.user;


import com.smartcode.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAll() throws SQLException;
    boolean deleteAll();
    boolean deleteById(Integer id);
    User getById(Integer id);
    boolean update(User user);
    void create(User user);












}