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
import com.example.handthishomathon.main.vm.SignUpVM;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class SignUpFragment extends Fragment {
    SignUpVM vm;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.GONE);
        vm = ViewModelProviders.of(this).get(SignUpVM.class);

        TextView goToSignIn = view.findViewById(R.id.go_to_sign_in);
        TextView signUpNow = view.findViewById(R.id.tv_sign_up_now);
        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signup_to_signin);
            }
        });
        signUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton toggleButton = view.findViewById(R.id.user_type);
                toggleButton.getText();
                if(toggleButton.getText().equals("Customer")){
                    Consumer consumer = createConsumerObject();
                    if(!consumer.getPhone().isEmpty()){
                        vm.signUpConsumer(consumer);

                        vm.consumerMutableLiveData.observe(getActivity(), new Observer<Consumer>() {
                            @Override
                            public void onChanged(Consumer consumer) {
                                vm.consumer = consumer;
                                if(consumer != null && !consumer.getId().isEmpty() && consumer.getId() != null){
                                    Toast.makeText(getActivity(), "Sign up was successful. You can Login", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.action_signup_to_signin);
                                }
                            }
                        });
                    } else {
                        Business business = createBusinessObject();
                        if(!business.getPhone().isEmpty()){
                            vm.signUpBusiness(business);
                            vm.businessMutableLiveData.observe(getActivity(), new Observer<Business>() {
                                @Override
                                public void onChanged(Business business) {
                                    vm.business = business;
                                    if(business != null && !business.getId().isEmpty() && business.getId() != null){
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
        TextInputLayout phone = getView().findViewById(R.id.phone_number);
        TextInputLayout password = getView().findViewById(R.id.password);
        TextInputLayout confirm_password = getView().findViewById(R.id.confirm_password);

        String phoneText = phone.getEditText().getText().toString();
        String passwordText = password.getEditText().getText().toString();
        String confirmPasswordText = confirm_password.getEditText().getText().toString();

        if(phoneText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()){
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if(!passwordText.equals(confirmPasswordText)){
            Toast.makeText(getActivity(), "Password and Confirm Password Fields don't match", Toast.LENGTH_SHORT).show();
        } else {
            consumer = new Consumer(null, "",phoneText,passwordText,"","",0.0,0.0);
        }
        return consumer;
    }

    private Business createBusinessObject() {
        Business business = new Business();
        TextInputLayout phone = getView().findViewById(R.id.phone_number);
        TextInputLayout password = getView().findViewById(R.id.password);
        TextInputLayout confirm_password = getView().findViewById(R.id.confirm_password);

        String phoneText = phone.getEditText().getText().toString();
        String passwordText = password.getEditText().getText().toString();
        String confirmPasswordText = confirm_password.getEditText().getText().toString();

        if(phoneText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()){
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else if(!passwordText.equals(confirmPasswordText)){
            Toast.makeText(getActivity(), "Password and Confirm Password Fields don't match", Toast.LENGTH_SHORT).show();
        } else {
            business = new Business(null, "",phoneText,passwordText,"","",0.0,0.0);
        }
        return business;
    }
}
