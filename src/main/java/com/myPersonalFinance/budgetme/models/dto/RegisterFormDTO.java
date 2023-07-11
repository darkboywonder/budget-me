package com.myPersonalFinance.budgetme.models.dto;

import com.myPersonalFinance.budgetme.models.dto.LoginFormDTO;

public class RegisterFormDTO extends LoginFormDTO {
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
