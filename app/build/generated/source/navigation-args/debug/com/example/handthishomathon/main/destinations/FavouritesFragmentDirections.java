package com.example.handthishomathon.main.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.example.handthishomathon.AppNavGraphDirections;

public class FavouritesFragmentDirections {
  private FavouritesFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionToHome() {
    return AppNavGraphDirections.actionToHome();
  }
}
