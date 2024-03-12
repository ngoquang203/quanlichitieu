package com.example.quanlichitieu.fragment;


import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.Expence;
import com.example.quanlichitieu.Income;
import com.example.quanlichitieu.Login;
import com.example.quanlichitieu.R;
import com.example.quanlichitieu.apdaptermanagement.Adapter_bill;
import com.example.quanlichitieu.apdaptermanagement.Bill_data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private int IDcollect,IDspent;
    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        Button buttonIncome = view.findViewById(R.id.transition_buttonImages);
        ListView viewPager2 = view.findViewById(R.id.transiton_viewpager2);
        TextView textView = view.findViewById(R.id.transiton_textViewErron);

        Adapter_bill adapterBill;

        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        IDcollect = sharedPreferences.getInt("IDcollect",0);
        IDspent = sharedPreferences.getInt("IDspent",0);
        // Assuming the actual ID is button_income
        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickButtonSort();
            }
        });
        // Inflate the layout for this fragment

        ArrayList<Bill_data> listData = new ArrayList<>();
        try {
            listData = Bill_data.getuserList(IDcollect,IDspent);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(listData.isEmpty()){
            textView.setVisibility(View.VISIBLE);
            viewPager2.setVisibility(View.GONE);
        }
        else{
            textView.setVisibility(View.GONE);
            viewPager2.setVisibility(View.VISIBLE);
        }
        adapterBill = new Adapter_bill(getContext(),listData);
        viewPager2.setAdapter(adapterBill);
        return view;
    }

    public void clickButtonSort(){
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_sort);
        dialog.setCancelable(true);
        dialog.show();
    }




}