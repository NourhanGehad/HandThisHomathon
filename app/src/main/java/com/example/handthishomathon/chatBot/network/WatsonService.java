package com.example.handthishomathon.chatBot.network;


import com.example.handthishomathon.chatBot.network.pojo.WatsonRequest;
import com.example.handthishomathon.chatBot.network.pojo.WatsonResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface WatsonService {
    @Headers("Content-Type: application/json")
    @POST("message")
    Call<WatsonResponse> getBotReplay(@Header("Authorization") String api_key, @Query("version")
            String version, @Body WatsonRequest mRequest);
}
