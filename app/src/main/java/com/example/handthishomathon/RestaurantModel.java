package com.example.handthishomathon;

public class RestaurantModel {

    private String RestaurantName, RestaurantAddress;
    private int RestaurantLogo;

    public RestaurantModel() {
    }


    public RestaurantModel(String restaurantName, String restaurantAddress, int restaurantLogo) {
        RestaurantName = restaurantName;
        RestaurantAddress = restaurantAddress;
        RestaurantLogo = restaurantLogo;
    }

    public int getRestaurantLogo() {
        return RestaurantLogo;
    }

    public void setRestaurantLogo(int restaurantLogo) {
        RestaurantLogo = restaurantLogo;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return RestaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        RestaurantAddress = restaurantAddress;
    }
}
