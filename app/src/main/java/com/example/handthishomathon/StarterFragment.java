package com.example.handthishomathon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StarterFragment extends Fragment {

    public StarterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView welcomeMessageTV = view.findViewById(R.id.tv_welcome);
        String welcomeMessage = getResources().getString(R.string.welcome_to_handthis);
        welcomeMessageTV.setText(Html.fromHtml(welcomeMessage));

        TextView start = view.findViewById(R.id.tv_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LandingActivity)getActivity()).hideStarterFragment();
            }
        });
    }
}
