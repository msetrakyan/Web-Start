package com.smartcode.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String age = req.getParameter("age");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(name);
        System.out.println(lastname);






    }




}
