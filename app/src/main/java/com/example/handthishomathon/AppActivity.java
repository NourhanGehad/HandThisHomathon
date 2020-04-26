package com.example.handthishomathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.google.gson.Gson;

public class AppActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    Consumer consumer;
    Business business;
    String userType = "N/A";
    protected IOnBackPressed onBackPressedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        setContentView(R.layout.activity_app);
        mPrefs = getPreferences(MODE_PRIVATE);

        Gson gson = new Gson();
        String json = "";
        userType = mPrefs.getString("user_type", "N/A");
        if(userType.equals("consumer")){
            json = mPrefs.getString("consumer", "N/A");
            if(json!="N/A"){
                consumer = gson.fromJson(json, Consumer.class);
                navHostFragment.getNavController().navigate(R.id.action_to_home);
            }
        } else if (userType.equals("business")){
            json = mPrefs.getString("business", "N/A");
            if(json!="N/A"){
                business = gson.fromJson(json, Business.class);
                navHostFragment.getNavController().navigate(R.id.action_to_home);
            }
        }

    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null){
            onBackPressedListener.onBackPressed();
        }
        else
            super.onBackPressed();
    }
}
