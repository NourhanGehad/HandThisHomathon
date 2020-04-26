package com.example.handthishomathon.main.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.handthishomathon.AppActivity;
import com.example.handthishomathon.BaseBackPressedListener;
import com.example.handthishomathon.R;
import com.example.handthishomathon.main.vm.SignInVM;
import com.example.handthishomathon.main.vm.SignUpVM;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.example.handthishomathon.model.LoginForm;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    SignInVM vm;
    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);
        vm = ViewModelProviders.of(this).get(SignInVM.class);

        TextView goToSignUp = view.findViewById(R.id.go_to_sign_up);
        TextView goToForgotPassword = view.findViewById(R.id.tv_go_to_forgot_password);
        TextView signInNow = view.findViewById(R.id.tv_sign_in_now);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Navigation.findNavController(view).navigate(R.id.action_signin_to_signup);
            }
        });
        goToForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signin_to_forgot_password);
            }
        });
        signInNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton toggleButton = view.findViewById(R.id.user_type);
                toggleButton.getText();
                LoginForm loginForm = createLoginForm();
                if(toggleButton.getText().equals("Customer")){
                    if(!loginForm.getPhone().isEmpty()){
                        vm.signInConsumer(loginForm);

                        vm.consumerMutableLiveData.observe(getActivity(), new Observer<Consumer>() {
                            @Override
                            public void onChanged(Consumer consumer) {
                                vm.consumer = consumer;
                                if(consumer != null && !consumer.getId().isEmpty() && consumer.getId() != null){
                                    Toast.makeText(getActivity(), "Sign in successful.", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_signin_to_home);
                                } else {
                                    Toast.makeText(getActivity(), "Sign in Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                } else {
                    if(!loginForm.getPhone().isEmpty()){
                        vm.signInBusiness(loginForm);

                        vm.businessMutableLiveData.observe(getActivity(), new Observer<Business>() {
                            @Override
                            public void onChanged(Business business) {
                                vm.business = business;
                                if(business != null && !business.getId().isEmpty() && business.getId() != null){
                                    Toast.makeText(getActivity(), "Sign in successful.", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_signin_to_home);
                                } else {
                                    Toast.makeText(getActivity(), "Sign in Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private LoginForm createLoginForm() {
        LoginForm loginForm = new LoginForm();
        TextInputLayout phone = getView().findViewById(R.id.phone_number);
        TextInputLayout password = getView().findViewById(R.id.password);

        String phoneText = phone.getEditText().getText().toString();
        String passwordText = password.getEditText().getText().toString();

        if(phoneText.isEmpty() || passwordText.isEmpty()){
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            loginForm = new LoginForm(phoneText,passwordText);
        }

        return loginForm;
    }
}

