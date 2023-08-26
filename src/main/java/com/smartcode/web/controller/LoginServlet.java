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

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserRepository userRepository = new UserRepositoryImpl();

        UserService userService = new UserServiceImpl(userRepository);



        try {
            List<User> list = userService.getAll();

            for(int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    resp.sendRedirect("profile.html");
                    break;
                }
            }


          } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}