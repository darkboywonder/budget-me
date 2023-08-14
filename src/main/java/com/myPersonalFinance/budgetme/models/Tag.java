//package com.myPersonalFinance.budgetme.models;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Tags {
//
//    @Id //primary key
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //generate values for primary key
//    private int id;
//
//    private String category;
//    @ManyToMany(mappedBy="categories")
//    private final List<Receipt> receipts = new ArrayList<>();
//
//    public Tag(String category) {
//        this.category = category;
//    }
//    public Tag() {};
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public List<Receipt> getReceipts() {
//        return receipts;
//    }
//}