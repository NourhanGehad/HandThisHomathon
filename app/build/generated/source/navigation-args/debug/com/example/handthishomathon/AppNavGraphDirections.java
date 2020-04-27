package com.example.handthishomathon;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class AppNavGraphDirections {
  private AppNavGraphDirections() {
  }

  @NonNull
  public static NavDirections actionToHome() {
    return new ActionOnlyNavDirections(R.id.action_to_home);
  }
}
