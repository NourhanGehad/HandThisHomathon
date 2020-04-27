package com.example.handthishomathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.handthishomathon.databinding.ActivityAppBinding;

import java.util.Objects;

public class AppActivity extends AppCompatActivity {

    protected IOnBackPressed onBackPressedListener;
    private NavHostFragment navHostFragment;
    private ActivityAppBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        //Hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        navHostFragment();

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
