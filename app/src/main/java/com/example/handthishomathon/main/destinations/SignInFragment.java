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

import com.example.handthishomathon.AppActivity;
import com.example.handthishomathon.BaseBackPressedListener;
import com.example.handthishomathon.R;
import com.example.handthishomathon.databinding.FragmentProfileBinding;
import com.example.handthishomathon.databinding.FragmentSignInBinding;
import com.example.handthishomathon.main.vm.SignInVM;
import com.example.handthishomathon.main.vm.SignUpVM;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;
import com.example.handthishomathon.model.LoginForm;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    SignInVM vm;
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
        vm = ViewModelProviders.of(this).get(SignInVM.class);

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
                LoginForm loginForm = createLoginForm();
                if (binding.userType.getText().equals("Customer")) {
                    vm.signInConsumer(loginForm);

                    vm.consumerMutableLiveData.observe(getActivity(), new Observer<JsonObject>() {
                        @Override
                        public void onChanged(JsonObject jsonObject) {
                            Log.d("seeThis", jsonObject.get("message").getAsString());
                            if(jsonObject.get("message").getAsString().equals("successful login")){
                                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                                JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                                JsonObject user = jsonArray.get(0).getAsJsonObject();
                                Consumer consumer = JsonObjectToConsumer(user);

                                Navigation.findNavController(view).navigate(R.id.action_signin_to_home);
                            } else {
                                Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                } else {
                    vm.signInBusiness(loginForm);

                    vm.businessMutableLiveData.observe(getActivity(), new Observer<JsonObject>() {
                        @Override
                        public void onChanged(JsonObject jsonObject) {
                            Log.d("seeThis", jsonObject.get("message").getAsString());
                            if(jsonObject.get("message").getAsString().equals("successful login")){
                                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                                JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                                JsonObject user = jsonArray.get(0).getAsJsonObject();
                                Business business = JsonObjectToBusiness(user);

                                Navigation.findNavController(view).navigate(R.id.action_signin_to_home);
                            } else {
                                Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }

    private LoginForm createLoginForm(){
        LoginForm loginForm = new LoginForm();
        String phoneText = binding.phoneNumber.getEditText().getText().toString();
        String passwordText = binding.password.getEditText().getText().toString();

        if (phoneText.isEmpty() || passwordText.isEmpty() ) {
            Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            loginForm = new LoginForm(phoneText, passwordText);
        }
        return loginForm;
    }

    private Consumer JsonObjectToConsumer(JsonObject user){
        Consumer consumer = new Consumer();
        if(user.get("_id").getAsString() != null)
            consumer.setId(user.get("_id").getAsString());
        if(user.get("name").getAsString() != null)
            consumer.setName(user.get("name").getAsString());
        if(user.get("phone").getAsString() != null)
            consumer.setPhone(user.get("phone").getAsString());
        if(user.get("password").getAsString() != null)
            consumer.setPassword(user.get("password").getAsString());
        if(user.get("chatbot_data_1").getAsString() != null)
            consumer.setChatbot_data_1(user.get("chatbot_data_1").getAsString());
        if(user.get("chatbot_data_2").getAsString() != null)
            consumer.setChatbot_data_2(user.get("chatbot_data_2").getAsString());
        if(user.get("longitude").getAsString() != null)
            consumer.setLongitude(user.get("longitude").getAsDouble());
        if(user.get("latitude").getAsString() != null)
            consumer.setLatitude(user.get("latitude").getAsDouble());

        return consumer;
    }
    private Business JsonObjectToBusiness(JsonObject user){
        Business business = new Business();
        if(user.get("_id").getAsString() != null)
            business.setId(user.get("_id").getAsString());
        if(user.get("name").getAsString() != null)
            business.setName(user.get("name").getAsString());
        if(user.get("phone").getAsString() != null)
            business.setPhone(user.get("phone").getAsString());
        if(user.get("password").getAsString() != null)
            business.setPassword(user.get("password").getAsString());
        if(user.get("chatbot_data_1").getAsString() != null)
            business.setChatbot_data_1(user.get("chatbot_data_1").getAsString());
        if(user.get("chatbot_data_2").getAsString() != null)
            business.setChatbot_data_2(user.get("chatbot_data_2").getAsString());
        if(user.get("longitude").getAsString() != null)
            business.setLongitude(user.get("longitude").getAsDouble());
        if(user.get("latitude").getAsString() != null)
            business.setLatitude(user.get("latitude").getAsDouble());

        return business;
    }
}
