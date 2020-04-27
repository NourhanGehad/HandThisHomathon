package com.example.handthishomathon.main.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.handthishomathon.R;
import com.example.handthishomathon.databinding.FragmentSignInBinding;
import com.example.handthishomathon.databinding.FragmentSignUpBinding;
import com.example.handthishomathon.main.vm.SignUpVM;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SignUpFragment extends Fragment {
    SignUpVM vm;
    private FragmentSignUpBinding binding;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);
        vm = ViewModelProviders.of(this).get(SignUpVM.class);

        binding.goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signup_to_signin);
            }
        });
        binding.tvSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.userType.getText();
                if (binding.userType.getText().equals("Customer")) {
                    Consumer consumer = createConsumerObject();
                    if (!consumer.getPhone().isEmpty()) {
                        vm.signUpConsumer(consumer);

                        vm.consumerMutableLiveData.observe(getActivity(), new Observer<Consumer>() {
                            @Override
                            public void onChanged(Consumer consumer) {
                                vm.consumer = consumer;
                                if (consumer != null && !consumer.getId().isEmpty() && consumer.getId() != null) {
                                    Toast.makeText(getActivity(), "Sign up was successful. You can Login", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_signup_to_signin);
                                }
                            }
                        });
                    } else {
                        Business business = createBusinessObject();
                        if (!business.getPhone().isEmpty()) {
                            vm.signUpBusiness(business);
                            vm.businessMutableLiveData.observe(getActivity(), new Observer<Business>() {
                                @Override
                                public void onChanged(Business business) {
                                    vm.business = business;
                                    if (business != null && !business.getId().isEmpty() && business.getId() != null) {
                                        Toast.makeText(getActivity(), "Sign up was successful. You can Login now", Toast.LENGTH_SHORT).show();
                                        Navigation.findNavController(view).navigate(R.id.action_signup_to_signin);
                                    }
                                }
                            });


                        }
                    }
                }


            }
        });
    }

    private Consumer createConsumerObject() {
        Consumer consumer = new Consumer();

        String phoneText = binding.phoneNumber.getEditText().getText().toString();
        String passwordText = binding.password.getEditText().getText().toString();
        String confirmPasswordText = binding.confirmPassword.getEditText().getText().toString();

        if (phoneText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (!passwordText.equals(confirmPasswordText)) {
            Toast.makeText(getActivity(), "Password and Confirm Password Fields don't match", Toast.LENGTH_SHORT).show();
        } else {
            consumer = new Consumer(null, "", phoneText, passwordText, "", "", 0.0, 0.0);
        }
        return consumer;
    }

    private Business createBusinessObject() {
        Business business = new Business();

        String phoneText = binding.phoneNumber.getEditText().getText().toString();
        String passwordText = binding.password.getEditText().getText().toString();
        String confirmPasswordText = binding.confirmPassword.getEditText().getText().toString();

        if (phoneText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if (!passwordText.equals(confirmPasswordText)) {
            Toast.makeText(getActivity(), "Password and Confirm Password Fields don't match", Toast.LENGTH_SHORT).show();
        } else {
            business = new Business(null, "", phoneText, passwordText, "", "", 0.0, 0.0);
        }
        return business;
    }
}
