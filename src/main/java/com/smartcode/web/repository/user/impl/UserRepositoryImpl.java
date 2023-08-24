package com.smartcode.web.repository.user.impl;



import com.smartcode.web.exceptions.ResourceNotFoundException;
import com.smartcode.web.model.User;
import com.smartcode.web.repository.user.UserRepository;
import com.smartcode.web.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection  connection = DataSource.getInstance().getConnection();


    public UserRepositoryImpl() {
        try {
            connection.createStatement()
                    .execute("CREATE TABLE if not exists users (id SERIAL PRIMARY KEY not null, " +
                            "name VARCHAR(30) not null, lastname Varchar(30) not null, middle_name varchar(30) , age integer, username varchar(30), password varchar(30) )");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public User create(User user) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, middle_name, age,username,password) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.execute();
            preparedStatement.close();

            ResultSet resultSet = connection.createStatement().executeQuery("""
                     SELECT * FROM users
                     ORDER BY id DESC
                     LIMIT 1;                                           
                    """);
            resultSet.next();
            Integer id = resultSet.getInt("id");
            resultSet.close();
            return getById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User update(User user) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                                
                    UPDATE users
                    SET name = ?,
                    lastname = ?,
                    middle_name = ?,
                    username = ?,
                    age = ?,
                    password = ?
                    WHERE id = ?
                    """);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> getAll() {

        List<User> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                User user = fromResultSet(resultSet);
                list.add(user);
            }

            resultSet.close();
            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User getById(Integer id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return fromResultSet(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new ResourceNotFoundException("User by id " + id + " not found");
    }

    private static User fromResultSet(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setAge(rs.getInt("age"));
            user.setPassword(rs.getString("password"));
            user.setMiddleName(rs.getString("middle_name"));
            user.setUsername(rs.getString("username"));
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User delete(Integer id) {
        User user = getById(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public List<User> findByName(String string) {

        List<User> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT * FROM users
                    WHERE substring(name,position(? in name),?)=?;
                    """);
            preparedStatement.setString(1, string);
            preparedStatement.setInt(2, string.length());
            preparedStatement.setString(3, string);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = fromResultSet(resultSet);
                list.add(user);
            }
            resultSet.close();
            preparedStatement.close();

            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public boolean deleteAll() {
        try {
        Statement statement =  connection.createStatement();

        statement.execute("""
                          DELETE FROM users
                            """);
        statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



}