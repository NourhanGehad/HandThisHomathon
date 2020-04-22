package com.example.handthishomathon.model;

import com.google.gson.annotations.SerializedName;

public class Consumer {
    @SerializedName("_id")
    String id = null;
    @SerializedName("name")
    String name = "";
    @SerializedName("phone")
    String phone = "";
    @SerializedName("password")
    String password = "";
    @SerializedName("chatbot_data_1")
    String chatbot_data_1 = "";
    @SerializedName("chatbot_data_2")
    String chatbot_data_2 = "";
    @SerializedName("longitude")
    Double longitude = 0.0;
    @SerializedName("latitude")
    Double latitude = 0.0;

    public Consumer() {
    }

    public Consumer(String id, String name, String phone, String password, String chatbot_data_1, String chatbot_data_2, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.chatbot_data_1 = chatbot_data_1;
        this.chatbot_data_2 = chatbot_data_2;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getChatbot_data_1() {
        return chatbot_data_1;
    }

    public void setChatbot_data_1(String chatbot_data_1) {
        this.chatbot_data_1 = chatbot_data_1;
    }

    public String getChatbot_data_2() {
        return chatbot_data_2;
    }

    public void setChatbot_data_2(String chatbot_data_2) {
        this.chatbot_data_2 = chatbot_data_2;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
