package com.example.handthishomathon.main.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.handthishomathon.AppNavGraphDirections;

public class MyOrdersFragmentDirections {
  private MyOrdersFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionToHome() {
    return AppNavGraphDirections.actionToHome();
  }
}
