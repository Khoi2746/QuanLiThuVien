/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.entity;

/**
 *
 * @author X1 Carbon
 */
public class User {
    private int userID;
    private String username;
    private String fullName;
    private String email;
    private String role;

    public User(int userID, String username, String fullName, String email, String role) {
        this.userID = userID;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    // Getter & Setter
    public int getUserID() { return userID; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    public void setUserID(int userID) { this.userID = userID; }
    public void setUsername(String username) { this.username = username; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}
