package com.smartcode.web.controller;

import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.repository.user.impl.UserRepositoryImpl;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.service.user.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastName");
        String age = req.getParameter("age");
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = new User(null, name, lastname, null, username, Integer.parseInt(age), password);

        UserRepository userRepository = new UserRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);

        userService.create(user);


        if(user != null) {
            resp.sendRedirect("login.html");

        }







    }


}