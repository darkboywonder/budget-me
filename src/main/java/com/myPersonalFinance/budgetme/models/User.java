package com.myPersonalFinance.budgetme.models;

import javax.persistence.*;


@Entity
@Table(name="user")
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate values for primary key
    private int id;

    @Column(nullable=false, unique = true)
    private String username;
    @Column
    private String password;

    private String accessToken;


    public User(){
    };

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


    public int getId() {

        return id;
    }

    public Object getPassword() {
        return password;
    }

}