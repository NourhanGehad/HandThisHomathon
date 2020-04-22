package com.example.handthishomathon.data;

import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;


import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static final String BASE_URL = "https://sleepy-earth-45632.herokuapp.com/";
    private  Services services;
    private static  Client INSTANCE;

    public Client() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        services = retrofit.create(Services.class);
    }

    public static Client getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new Client();
        }
        return INSTANCE;
    }

    public Observable<Consumer> signUpConsumer(Consumer consumer){
        return services.signUpConsumer(consumer);
    }
    public Observable<Business> signUpBusiness(Business business){
        return services.signUpBusiness(business);
    }

}
