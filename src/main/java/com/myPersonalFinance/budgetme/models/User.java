package com.myPersonalFinance.budgetme.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy = "user")
    private List<PlaidAccessToken> plaidAccessTokens = new ArrayList<>();



    public User(){
    };


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

    public List<PlaidAccessToken> getPlaidAccessTokens() {
        return plaidAccessTokens;
    }


}