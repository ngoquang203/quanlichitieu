package com.example.quanlichitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quanlichitieu.apdaptermanagement.CategoryAdapter;
import com.example.quanlichitieu.managementdata.ServiceSpent;
import com.example.quanlichitieu.managementdata.Serviceapp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Expence extends AppCompatActivity {
    private List<ServiceSpent> serviceappList;
    private Spinner spinner;
    private CategoryAdapter categoryAdapter;
    private TextView dateTextInputEditText,timeTextInputEditText;

    private void Init(){
        spinner = findViewById(R.id.expence_spinner);
        categoryAdapter = new CategoryAdapter(this,R.layout.item_selected,serviceappList);
        spinner.setAdapter(categoryAdapter);
        dateTextInputEditText = findViewById(R.id.expence_date);
        timeTextInputEditText = findViewById(R.id.expence_time);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence);
        try {
            serviceappList = ServiceSpent.getuserlist();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Init();
        clickEditText();
    }
    private void clickEditText(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


        timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
        dateTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));

        dateTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate(calendar,simpleDateFormat);
            }
        });
        timeTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTime(calendar,simpleTimeFormat);
            }
        });
    }
    private void selectedTime(Calendar calendar,SimpleDateFormat simpleTimeFormat){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
            }
        },hour,minute,true);
        timePickerDialog.show();
    }
    private void selectedDate(Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                dateTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();

    }
}