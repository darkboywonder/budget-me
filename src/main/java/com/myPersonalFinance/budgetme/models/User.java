package com.myPersonalFinance.budgetme.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class User {

    @Id //primary key
    @GeneratedValue //generate values for primary key
    private int id;

    @Column
    private String username;
    @Column
    private String password;


//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User(){
    };

    private String accessToken;


    public String getAccessToken() {

        return accessToken;
    }

    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) { //why do we need a setter for password?
        this.password = password;
    }

    //    public boolean isMatchingPassword(String password) {
//
//        return encoder.matches(password, pwHash);
//    }

    public int getId() {

        return id;
    }

}