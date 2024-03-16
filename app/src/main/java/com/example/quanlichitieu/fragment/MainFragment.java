package com.example.quanlichitieu.fragment;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.Expence;
import com.example.quanlichitieu.Income;
import com.example.quanlichitieu.PlanMoney;
import com.example.quanlichitieu.R;
import com.example.quanlichitieu.managementdata.CollectMoney;
import com.example.quanlichitieu.managementdata.SpentMoney;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;


public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private View view;
    private SharedPreferences sharedPreferences;
    private long sumCollect,sumSpent;
    private Button addIncomeButton,addExpneceButton,buttonPlanMoney;
    private TextView textMonth,SumCollect,SumSpent,SumNow;
    private void Init(View view){

        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        int IDuser = sharedPreferences.getInt("IDuser",0);
        try {
            sumCollect = CollectMoney.getuserlist(IDuser).getSumCollect();
            sumSpent = SpentMoney.getuserlist(IDuser).getSumSpent();
            sharedPreferences.edit().putLong("SumCollect",sumCollect).apply();
            sharedPreferences.edit().putLong("SumSpent",sumSpent).apply();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SumCollect = view.findViewById(R.id.main_sumCollect);
        SumSpent = view.findViewById(R.id.main_sumSpent);
        SumNow = view.findViewById(R.id.main_sumNow);
        addIncomeButton = view.findViewById(R.id.main_addIncomeButton);
        addExpneceButton = view.findViewById(R.id.main_addExpenceButton);
        buttonPlanMoney = view.findViewById(R.id.main_buttonPlanMoney);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        Init(view);
        setData();
        clickButton();
        return view;
    }

    private void clickButton() {
        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Income.class);
                startActivity(intent);
            }
        });
        addExpneceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Expence.class);
                startActivity(intent);
            }
        });
        buttonPlanMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlanMoney.class);
                startActivity(intent);
            }
        });


    }

    private void setData() {
        DecimalFormat df = new DecimalFormat("###,###,###.## VND");
        LocalDate today = LocalDate.now();
        textMonth = view.findViewById(R.id.main_month);
        textMonth.setText("Tháng "+ today.getMonthValue());
        SumCollect.setText(df.format(sumCollect));
        SumSpent.setText(df.format(sumSpent));
        SumNow.setText(df.format(sumCollect - sumSpent));
    }
}