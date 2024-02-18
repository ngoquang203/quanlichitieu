package com.example.quanlichitieu.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlichitieu.Expence;
import com.example.quanlichitieu.Income;
import com.example.quanlichitieu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add,
                container, false);
        Button addIncome = (Button) view.findViewById(R.id.add_income);
        Button addExpence = (Button) view.findViewById(R.id.add_expence);
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFragment.this.getActivity(), Income.class);
                startActivity(intent);
            }
        });
        addExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFragment.this.getActivity(), Expence.class);
                startActivity(intent);
            }
        });
        return view;
    }
}