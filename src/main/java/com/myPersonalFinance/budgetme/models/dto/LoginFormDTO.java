package com.myPersonalFinance.budgetme.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//DTO associated with a form as an object that represents each of the form fields
public class LoginFormDTO {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 20, message = "Invalid username. Must be at least 4 characters and no more than 20 characters.")
    private String username;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Password must have at least one uppercase letter, one number, and one symbol.")
    @Size(min = 7, max = 50, message = "Invalid password. Must be at least 7 characters and no more than 50 characters.")
    private String password;

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
}
