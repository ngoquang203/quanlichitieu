package com.example.quanlichitieu.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlichitieu.Login;
import com.example.quanlichitieu.R;

import java.util.Objects;


public class ProfileFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Button button = view.findViewById(R.id.profile_loginOut);
        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("login",false).apply();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}