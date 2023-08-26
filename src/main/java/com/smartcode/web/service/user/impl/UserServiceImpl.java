package com.smartcode.web.service.user.impl;




import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.service.user.UserService;
import com.smartcode.web.utils.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Connection connection = DataSource.getInstance().getConnection();
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {

        try {

            connection.setAutoCommit(false);

            List<User> list = userRepository.getAll();

            connection.commit();

            connection.setAutoCommit(true);

            return list;

        } catch (Throwable e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public void create(User user) {
        try {
            connection.setAutoCommit(false);

            userRepository.create(user);

            connection.commit();

            connection.setAutoCommit(true);

        } catch (Throwable e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public boolean deleteAll() {
        try {
            connection.setAutoCommit(false);

            userRepository.deleteAll();

            connection.commit();

            connection.setAutoCommit(true);

            return true;
        } catch (Throwable e) {
            System.out.println(e.getCause());
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return false;
        }
    }




    public boolean deleteById(Integer id) {

        try {
            connection.setAutoCommit(false);

            userRepository.delete(id);

            connection.commit();

            connection.setAutoCommit(true);

            return true;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public User getById(Integer id) {

        try {
            connection.setAutoCommit(false);

             User user =  userRepository.getById(id);
             connection.commit();
             connection.setAutoCommit(true);
          return user;
        } catch (Throwable e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    public boolean update(User user) {
        try {
            connection.setAutoCommit(false);

            userRepository.update(user);

            connection.commit();

            connection.setAutoCommit(true);

            return true;

        } catch (Throwable e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
    }





}