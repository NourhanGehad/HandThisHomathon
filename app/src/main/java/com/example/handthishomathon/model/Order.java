package com.example.handthishomathon.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("_id")
    String id = null;
    @SerializedName("order_body")
    String order_body = "";
    @SerializedName("date")
    String date = "";
    @SerializedName("consumer_id")
    String consumer_id = "";
    @SerializedName("business_id")
    String business_id = "";
    @SerializedName("status")
    String status = "";
    @SerializedName("delivered_on")
    String delivered_on = "";
    @SerializedName("comments")
    String comments = "";
    @SerializedName("total")
    Double total = 0.0;
    @SerializedName("currency")
    String currency = "";
    @SerializedName("longitude")
    Double longitude = 0.0;
    @SerializedName("latitude")
    Double latitude = 0.0;

    public Order() {
    }

    public Order(String id, String order_body, String date, String consumer_id, String business_id, String status, String delivered_on, String comments, Double total, String currency, Double longitude, Double latitude) {
        this.id = id;
        this.order_body = order_body;
        this.date = date;
        this.consumer_id = consumer_id;
        this.business_id = business_id;
        this.status = status;
        this.delivered_on = delivered_on;
        this.comments = comments;
        this.total = total;
        this.currency = currency;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_body() {
        return order_body;
    }

    public void setOrder_body(String order_body) {
        this.order_body = order_body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivered_on() {
        return delivered_on;
    }

    public void setDelivered_on(String delivered_on) {
        this.delivered_on = delivered_on;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
