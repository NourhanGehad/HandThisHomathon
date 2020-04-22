package com.example.handthishomathon.main.destinations;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.example.handthishomathon.R;

public class SignUpFragmentDirections {
  private SignUpFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSignupToSignin() {
    return new ActionOnlyNavDirections(R.id.action_signup_to_signin);
  }
}
