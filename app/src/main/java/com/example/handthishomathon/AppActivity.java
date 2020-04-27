package com.example.handthishomathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.handthishomathon.databinding.ActivityAppBinding;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.google.gson.Gson;

import java.util.Objects;

public class AppActivity extends AppCompatActivity {

    protected IOnBackPressed onBackPressedListener;
    private NavHostFragment navHostFragment;
    private ActivityAppBinding binding;
    private View view;
    public SharedPreferences mPrefs;
    public static String userType = "N/A";
    public static Consumer currentConsumer = new Consumer();
    public static Business currentBusiness = new Business();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        //Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        navHostFragment();
        mPrefs = getPreferences(MODE_PRIVATE);
        userType = mPrefs.getString("user_type", "N/A");
        if(!userType.equals("N/A")){
            Gson gson = new Gson();
            if(userType.equals("consumer")){
                String json = mPrefs.getString("consumer", "");
                currentConsumer = gson.fromJson(json, Consumer.class);
            } else if (userType.equals("business")) {
                String json = mPrefs.getString("business", "");
                currentBusiness = gson.fromJson(json, Business.class);
            }

            navHostFragment.getNavController().navigate(R.id.action_to_home);
        }
    }

    public void setOnBackPressedListener(IOnBackPressed onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.onBackPressed();
        } else
            super.onBackPressed();
    }

    private void navHostFragment() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavBar, Objects.requireNonNull(navHostFragment).getNavController());
    }
}
