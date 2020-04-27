package com.example.handthishomathon.main.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.handthishomathon.AppActivity;
import com.example.handthishomathon.BaseBackPressedListener;
import com.example.handthishomathon.R;
import com.example.handthishomathon.databinding.FragmentProfileBinding;
import com.example.handthishomathon.databinding.FragmentSignInBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity) getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);


        binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signin_to_signup);
            }
        });
        binding.tvGoToForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signin_to_forgot_password);
            }
        });
        binding.tvSignInNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signin_to_home);
            }
        });
    }
}
