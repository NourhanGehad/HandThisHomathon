package com.example.handthishomathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppActivity extends AppCompatActivity {

    protected IOnBackPressed onBackPressedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
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
