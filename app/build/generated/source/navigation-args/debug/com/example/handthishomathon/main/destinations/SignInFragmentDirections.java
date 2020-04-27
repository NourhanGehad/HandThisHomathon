package com.example.handthishomathon.main.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.handthishomathon.AppNavGraphDirections;
import com.example.handthishomathon.R;

public class SignInFragmentDirections {
  private SignInFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSigninToSignup() {
    return new ActionOnlyNavDirections(R.id.action_signin_to_signup);
  }

  @NonNull
  public static NavDirections actionSigninToForgotPassword() {
    return new ActionOnlyNavDirections(R.id.action_signin_to_forgot_password);
  }

  @NonNull
  public static NavDirections actionSigninToHome() {
    return new ActionOnlyNavDirections(R.id.action_signin_to_home);
  }

  @NonNull
  public static NavDirections actionToHome() {
    return AppNavGraphDirections.actionToHome();
  }
}
