package com.example.quanlichitieu.fragment;


import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlichitieu.Login;
import com.example.quanlichitieu.R;
import com.example.quanlichitieu.SignUp;
import com.example.quanlichitieu.apdaptermanagement.Adapter_bill;
import com.example.quanlichitieu.apdaptermanagement.Bill_data;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private int IDcollect,IDspent;
    public TransactionFragment() {
        // Required empty public constructor
    }

    private ListView viewPager2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
//        Button buttonIncome = view.findViewById(R.id.transition_buttonImages);
        viewPager2 = view.findViewById(R.id.transiton_viewpager2);
        viewPager2.setDivider(null);
        TextView textView = view.findViewById(R.id.transiton_textViewErron);

        Adapter_bill adapterBill;

        sharedPreferences = getActivity().getSharedPreferences("loginData",MODE_PRIVATE);
        IDcollect = sharedPreferences.getInt("IDcollect",0);
        IDspent = sharedPreferences.getInt("IDspent",0);
        // Assuming the actual ID is button_income
//        buttonIncome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                clickButtonSort();
//            }
//        });
        // Inflate the layout for this fragment

        ArrayList<Bill_data> listData = new ArrayList<>();
        try {
            listData = Bill_data.getuserList(IDcollect,IDspent);
            Collections.sort(listData, Collections.reverseOrder());

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
        ArrayList<Bill_data> finalListData = listData;
        viewPager2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill_data billData = finalListData.get(position);
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.activity_detail_category);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                ImageButton button = dialog.findViewById(R.id.detialCategory_button);
                TextView money = dialog.findViewById(R.id.detailCategory_money);
                TextView title = dialog.findViewById(R.id.detailCategory_title);
                TextView content = dialog.findViewById(R.id.detailCategory_content);
                TextView date = dialog.findViewById(R.id.detailCategory_date);
                TextView moneyNow = dialog.findViewById(R.id.detailCategory_moneyNow);
                DecimalFormat df = new DecimalFormat("###,###,###.## VND");
                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(billData.getIdDataCategory()%2==0){
                    money.setText("-"+df.format(billData.getPrice()));
                    money.setTextColor(Color.RED);
                }
                else{

                    money.setText("+"+ df.format(billData.getPrice()));
                    money.setTextColor(Color.GREEN);
                }
                title.setText(billData.getNameservice());
                content.setText(billData.getContent());
                date.setText(simpleDateFormat.format(billData.getDates()) + ", " + simpleTimeFormat.format(billData.getTimes()));
                moneyNow.setText(df.format(billData.getMoneyNow()));
                dialog.show();
            }
        });
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