package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quanlichitieu.managementdata.PlanMonney;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlanMoney extends AppCompatActivity {
    private LinearLayout add,list;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_money);

        Init();
        clickBackButton();
        clickAddPlanMoney();
        clickManagementList();

    }

    private void clickBackButton() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMoney.this,Home.class);
                startActivity(intent);
            }
        });
    }

    private void clickManagementList() {
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlanMoney.this,ManagementPlanMoney.class);
                startActivity(intent);
            }
        });
    }

    private void clickAddPlanMoney() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanMoney.this,AddPlanMoney.class);
                startActivity(intent);
            }
        });
    }

    private void Init() {
        add = findViewById(R.id.planMoney_add);
        list = findViewById(R.id.planMoney_list);
        imageButton = findViewById(R.id.planMoney_back);
    }
}