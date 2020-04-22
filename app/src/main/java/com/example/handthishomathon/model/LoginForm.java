package com.example.handthishomathon.model;

import com.google.gson.annotations.SerializedName;

public class LoginForm {
    @SerializedName("phone")
    String phone = "";
    @SerializedName("password")
    String password = "";

    public LoginForm() {
    }

    public LoginForm(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
