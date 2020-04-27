package com.example.handthishomathon.main.destinations;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handthishomathon.ChatBotActivity;
import com.example.handthishomathon.R;
import com.example.handthishomathon.RestaurantModel;
import com.example.handthishomathon.databinding.FragmentForgotPasswordBinding;
import com.example.handthishomathon.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements RestaurantAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private RestaurantAdapter mRestaurantAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_bar);
        navBar.setVisibility(View.VISIBLE);
        mRestaurantAdapter = new RestaurantAdapter(HomeFragment.this);
        List<RestaurantModel> mRestaurantList = new ArrayList<>();
        mRestaurantList.add(new RestaurantModel("KFC","KFC, Jeddah",R.drawable.kfc));
        mRestaurantList.add(new RestaurantModel("ALBAIK","ALBAIK, Jeddah",R.drawable.albaik));
        mRestaurantList.add(new RestaurantModel("BURGER KING","BURGER KING, Jeddah",R.drawable.burger_king));
        mRestaurantAdapter.addRestaurant(mRestaurantList);
        binding.rvRestaurants.setAdapter(mRestaurantAdapter);
        binding.rvRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent chatBotIntent = new Intent(getContext(), ChatBotActivity.class);
        // todo in future send client name to chatBot Header
        startActivity(chatBotIntent);
    }
}
