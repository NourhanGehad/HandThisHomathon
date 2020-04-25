package com.example.handthishomathon.chatBot.network;

import android.util.Base64;

import com.example.handthishomathon.chatBot.network.pojo.WatsonRequest;
import com.example.handthishomathon.chatBot.network.pojo.WatsonResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WatsonClient {
    //ToDo Change your base url , workspace_id , api_key and version date(skill creation date)
    private static final String BASE_URL = "https://api.eu-gb.assistant.watson.cloud.ibm.com/instances/ead26a18-6a29-4efa-8a07-f6dcbd73e4bf/v1/workspaces/";
    private static final String AR_WORKSPACE_ID = "xx....xx/";
    private static final String EN_WORKSPACE_ID = "8772fe02-3853-469f-bc83-2d7dc26649d5/";
    //    final String WORKSPACE_LANGUAGE_ID = getDeviceLanguage();
    String baseAuth = "apikey" + ":" + "6VT29CnZDl0fbUFm8OrZE_or2ocYC-W9Mivh5nR5J_IY";
    String apiKey = Base64.encodeToString(baseAuth.getBytes(), Base64.NO_WRAP);
    String authorization = "Basic " + apiKey;

    private WatsonService mService;
    private static WatsonClient INSTANCE;

    public WatsonClient() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + EN_WORKSPACE_ID)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = mRetrofit.create(WatsonService.class);
    }

    public static WatsonClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WatsonClient();
        return INSTANCE;
    }

    public Call<WatsonResponse> getBotReplay(WatsonRequest mRequest) {
        return mService.getBotReplay(authorization, "2020-04-22", mRequest);
    }

//    private String getDeviceLanguage() {
//        if (Locale.getDefault().getLanguage().equalsIgnoreCase("en"))
//            return EN_WORKSPACE_ID;
//        else return AR_WORKSPACE_ID;
//    }

}