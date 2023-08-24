package com.smartcode.web.repository.user;


import com.smartcode.web.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user);
    User update(User user);
    List<User> getAll();
    User getById(Integer id);
    User delete(Integer id);
    List<User> findByName(String string);
    boolean deleteAll();






}
