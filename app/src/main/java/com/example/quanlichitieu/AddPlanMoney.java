package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlichitieu.managementdata.PlanMonney;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddPlanMoney extends AppCompatActivity {
    private TextView buttonIncome;
    private TextView buttonExpence;
    private TextView buttonAdd,supMoney;
    private Spinner spinner;
    private TextInputEditText money;
    private TextInputEditText content;
    private ImageButton imageButton;
    private String strSpinner;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> listSpiner;
    private int IDuser,IDcollect,IDspent;
    private int index;
    private DecimalFormat df = new DecimalFormat("###,###,###.## VND");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_money);
        Init();
        clickButtonBack();
        clickChoseMoneyIncomeOrExpence();
        clickAddNewPlanMoney();
        getSelectTedSpinner();
        updateSupMoney();
    }

    private void updateSupMoney() {
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    supMoney.setText(df.format(Long.valueOf(s.toString())));
                }
                else{
                    supMoney.setText("0 VND");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void clickButtonBack() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlanMoney.this,PlanMoney.class);
                startActivity(intent);
            }
        });
    }

    private void clickAddNewPlanMoney() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date dateStart = calendar.getTime();
                Date dateEnd = calendar.getTime();
                if(strSpinner == listSpiner.get(0)){
                    calendar.add(Calendar.DATE,7);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(1)){
                    calendar.add(Calendar.MONTH,1);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(2)){
                    calendar.add(Calendar.MONTH,3);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(3)){
                    calendar.add(Calendar.MONTH,6);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(4)){
                    calendar.add(Calendar.YEAR,1);
                    dateEnd = calendar.getTime();
                }else if(strSpinner == listSpiner.get(5)){
                    calendar.add(Calendar.MONTH,2);
                    dateEnd = calendar.getTime();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formatDateStart = sdf.format(dateStart);
                String formatDateEnd = sdf.format(dateEnd);
                String strMoney = money.getText().toString();
                String strContent = content.getText().toString();
                if(strMoney.length() > 0 && strContent.length() > 0){

                    try {
                        int id = 0;
                        if(index == 1)
                            id = IDcollect;
                        else
                            id = IDspent;
                        PlanMonney.addPlanMoney(IDuser,id,strMoney,formatDateStart,formatDateEnd,strContent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(AddPlanMoney.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPlanMoney.this,PlanMoney.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddPlanMoney.this, "False", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getSelectTedSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSpinner = listSpiner.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void clickChoseMoneyIncomeOrExpence() {
        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                buttonIncome.getBackground().setTint(ContextCompat.getColor(AddPlanMoney.this,R.color.blue80));
                buttonExpence.getBackground().setTint(ContextCompat.getColor(AddPlanMoney.this,R.color.light20));
            }
        });
        buttonExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                buttonIncome.getBackground().setTint(ContextCompat.getColor(AddPlanMoney.this,R.color.light20));
                buttonExpence.getBackground().setTint(ContextCompat.getColor(AddPlanMoney.this,R.color.blue80));
            }
        });
    }

    private void Init() {
        buttonIncome = findViewById(R.id.addPlanMoney_buttonIncome);
        buttonExpence = findViewById(R.id.addPlanMoney_Expence);
        buttonAdd = findViewById(R.id.addPlanMoney_buttonAdd);
        spinner = findViewById(R.id.addPlaneMoney_spinner);
        money = findViewById(R.id.addPlanMoney_money);
        content = findViewById(R.id.addPlanMoney_content);
        imageButton = findViewById(R.id.addPlaneMoney_back);
        supMoney = findViewById(R.id.addPlanMoney_Supmoney);
        listSpiner = new ArrayList<>();
        listSpiner.add("1 tuần");
        listSpiner.add("1 tháng");
        listSpiner.add("3 tháng");
        listSpiner.add("6 tháng");
        listSpiner.add("1 năm");
        listSpiner.add("2 năm");
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        IDuser = sharedPreferences.getInt("IDuser",0);
        IDcollect = sharedPreferences.getInt("IDcollect",0);
        IDspent = sharedPreferences.getInt("IDspent",0);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,listSpiner));
        index = 1;
    }
}