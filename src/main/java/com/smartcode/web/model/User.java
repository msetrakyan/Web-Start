package com.smartcode.web.model;

public class User {

    private Integer id;
    private String name;
    private String lastName;
    private String middleName;
    private String username;
    private Integer age;
    private String password;

    public User(Integer id, String name, String lastName, String middleName, String username, Integer age, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }


    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}