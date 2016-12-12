/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prise.model;

/**
 *
 * @author QuartierLatin
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private String email;
    private int privilage;
    private String name;

    public User() {
    }

    public User(int userId, String username, String password, String email, int privilage) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.privilage = privilage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPrivilage() {
        return privilage;
    }

    public void setPrivilage(int privilage) {
        this.privilage = privilage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email + ", privilage=" + privilage + ", name=" + name + '}';
    }

}
