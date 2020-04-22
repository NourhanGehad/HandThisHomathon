package com.example.handthishomathon.data;


import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.example.handthishomathon.model.LoginForm;
import com.example.handthishomathon.model.Order;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Services {

    @GET("/order/orders")
    public Observable<List<Order>> getOrders();

    @GET("/consumer/consumers")
    public Observable<List<Consumer>> getConsumers();

    @GET("/business/businesses")
    public Observable<List<Business>> getBusinesses();

    @POST("consumer/create-consumer")
    public Observable<Consumer> signUpConsumer(@Body Consumer consumer);

    @POST("business/create-business")
    public Observable<Business> signUpBusiness(@Body Business business);

    @HTTP(method = "GET", path = "consumer/login-consumer", hasBody = true)
    public Observable<Consumer> loginConsumer(@Body LoginForm loginForm);

    @GET("business/login-business")
    @HTTP(method = "GET", path = "consumer/login-business", hasBody = true)
    public Observable<Business> loginBusiness(@Body LoginForm loginForm);
    
    @POST("order/create-order")
    public Observable<Order> addNewOrder(@Body Order order);

    @PUT("consumer/update-consumer/{id}")
    public Observable<Consumer> editProfileConsumer(@Path("id") String id, @Body Consumer consumer);

    @PUT("business/update-business/{id}")
    public Observable<Business> editProfileBusiness(@Path("id") String id, @Body Business business);

    @PUT("order/update-order/{id}")
    public Observable<Order> updateOrder(@Path("id") String id, @Body Order order);


}
